package com.lingan.edongspeechlibrary.speech;

import android.content.Context;
import android.util.Log;

import com.aispeech.AIError;
import com.aispeech.AIResult;
import com.aispeech.IMergeRule;
import com.aispeech.common.AIConstant;
import com.aispeech.common.JSONResultParser;
import com.aispeech.common.Util;
import com.aispeech.export.engines.AILocalGrammarEngine;
import com.aispeech.export.engines.AILocalSignalAndWakeupEngine;
import com.aispeech.export.engines.AILocalTTSEngine;
import com.aispeech.export.engines.AIMixASREngine;
import com.aispeech.export.listeners.AIASRListener;
import com.aispeech.export.listeners.AILocalGrammarListener;
import com.aispeech.export.listeners.AILocalSignalAndWakeupListener;
import com.aispeech.export.listeners.AITTSListener;
import com.lingan.edongspeechlibrary.bean.ResultBean;
import com.lingan.edongspeechlibrary.led.LedControl;
import com.lingan.edongspeechlibrary.media.MediaUtil;
import com.lingan.edongspeechlibrary.record.Record;
import com.lingan.edongspeechlibrary.utils.Constant;
import com.lingan.edongspeechlibrary.utils.GrammarHelper;
import com.lingan.edongspeechlibrary.utils.WifiUtil;


import org.litepal.util.LogUtil;

import java.io.File;

/**
 * Created by dyx on 2017/12/20.
 * 思必驰引擎
 * 唤醒、识别各类回调
 */

public class EngineSpeech {

    private AILocalTTSEngine mAILocalTtsEngine;
    private AILocalGrammarEngine mAILocalGrammarEngine;
    private AIMixASREngine mAIMixASREngine;
    private AILocalSignalAndWakeupEngine mAILocalSignalAndWakeupEngine;
    private Context context;

    private boolean isNeedASR;

    private static EngineSpeech speechEngine;

    private ResultBean resultBean;

    private EngineSpeech(Context context) {
        this.context = context;
    }

    public static EngineSpeech getInstance(Context context) {
        if (speechEngine != null)
            return speechEngine;
        speechEngine = new EngineSpeech(context);
        return speechEngine;

    }

    /**
     * 初始化 语音合成、语法引擎
     */
    public void initTtsGrammarEngine() {

        // 本地合成引擎
        if (mAILocalTtsEngine != null)
            mAILocalTtsEngine.destroy();
        mAILocalTtsEngine = AILocalTTSEngine.createInstance();
        mAILocalTtsEngine.setResource("zhilingf_common_param_ce_local.v2.006.bin");
        mAILocalTtsEngine.setDictDbName("aitts_sent_dict_v3.24.db");
        mAILocalTtsEngine.init(context, new AILocalTTSListenerImpl(), Constant.APPKEY, Constant.SECRETKEY);
        mAILocalTtsEngine.setSpeechRate(0.85f);  // 语速设置
        mAILocalTtsEngine.setDeviceId(Util.getIMEI(context));

        // 识别引擎 - - - 本地语法、混合语义
        if (mAILocalGrammarEngine != null)
            mAILocalGrammarEngine.destroy();
        mAILocalGrammarEngine = AILocalGrammarEngine.createInstance();
        mAILocalGrammarEngine.setResFileName("ebnfc.aihome.0.3.0.bin");
        mAILocalGrammarEngine.init(context, new AILocalGrammarListenerImpl(), Constant.APPKEY, Constant.SECRETKEY);
        mAILocalGrammarEngine.setDeviceId(Util.getIMEI(context));

    }

    /**
     * 语法更新完成
     * 初始化 混合语义引擎
     */
    private void initMixAsrEngine() {
        if (mAIMixASREngine != null)
            mAIMixASREngine.destroy();
        mAIMixASREngine = AIMixASREngine.createInstance();
        mAIMixASREngine.setResBin("ebnfr.aihome.0.3.0.bin");
        mAIMixASREngine.setNetBin(AILocalGrammarEngine.OUTPUT_NAME, true);
        mAIMixASREngine.setVadResource("vad_aihome_v0.7.bin");
        mAIMixASREngine.setServer("ws://s.api.aispeech.com:1028,ws://s.api.aispeech.com:80"); //灰度环境
        // mAIMixASREngine.setServer("ws://s.api.aispeech.com:1028,ws://s.api.aispeech.com:80"); //正式产品环境
        mAIMixASREngine.setUseXbnfRec(true);
        mAIMixASREngine.setRes("aihome");
        //mAIMixASREngine.setUsePinyin(true);
        mAIMixASREngine.setUseForceout(false);
        mAIMixASREngine.setAthThreshold(0.6f);
        mAIMixASREngine.setIsRelyOnLocalConf(true);
        mAIMixASREngine.setIsPreferCloud(false);
        mAIMixASREngine.setWaitCloudTimeout(5000);
        // dyx change 200-->1000
        mAIMixASREngine.setPauseTime(1000);
        mAIMixASREngine.setUseConf(true);
//        mAsrEngine.setVersion("1.0.4"); //设置语义版本号，用哪个版本请在项目启动时和思必驰项目经理确认
        mAIMixASREngine.setNoSpeechTimeOut(0);

        // change
        mAIMixASREngine.setCloudVadEnable(false);

        if (context.getExternalCacheDir() != null) {
            // mAIMixASREngine.setUploadEnable(false);//设置上传音频使能
            File dir = new File("/sdcard/asr/");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            mAIMixASREngine.setTmpDir(dir.getAbsolutePath());//设置上传的音频保存在本地的目录
        }

        mAIMixASREngine.init(context, new AIASRListenerImpl(), Constant.APPKEY, Constant.SECRETKEY);
        mAIMixASREngine.setUseCloud(true);//该方法必须在init之后
        //cn.sds为云端对话服务，cn.dlg.ita为云端语义服务，默认为云端语义,想要访问对话服务时，才设置为cn.sds，否则不用设置
        mAIMixASREngine.setCoreType("cn.sds");

    }


    /**
     * 合成、语法、识别引擎初始化完成
     * 最后初始化 唤醒引擎
     */
    private void initWakeupEngine() {
        mAILocalSignalAndWakeupEngine = AILocalSignalAndWakeupEngine.getInstance();
        // 设置回声消除文件
        mAILocalSignalAndWakeupEngine.setAecCfg("AEC_ch8-2-ch6_1ref_comm_20171208_v0.9.0.bin");
        //mAILocalSignalAndWakeupEngine.disableAec();

        mAILocalSignalAndWakeupEngine.setRollBackTime(1200);
        mAILocalSignalAndWakeupEngine.setWords(new String[]{
                "ni hao xiao le"});
        mAILocalSignalAndWakeupEngine.setThreshold(new float[]{0.24f});
        mAILocalSignalAndWakeupEngine.setMajors(new int[]{0});


        // 设置 beamformingcfg 配置文件
        mAILocalSignalAndWakeupEngine.setBeamformingCfg("UCA_asr_ch6-2-ch6_72mm_common_20171108_v1.0.2.bin");
        // 设置唤醒词资源名
        mAILocalSignalAndWakeupEngine.setResBin("wakeup_aifar_comm_20180104.bin");
        // 设置唤醒配置文件
        // mAILocalSignalAndWakeupEngine.setWakeupCfg("UCA_wakeup_ch6-2-ch6_72mm_common_20170725_v3.0.12.bin");
        //mAILocalSignalAndWakeupEngine.setSaveAudioFilePath("/mnt/internal_sd/");
        // 设置 自己 feed 数据
        mAILocalSignalAndWakeupEngine.setUseCustomFeed(true);

        mAILocalSignalAndWakeupEngine.init(context, new AILocalSignalAndWakeupListenerImpl(), Constant.APPKEY, Constant.SECRETKEY);


    }


    /**
     * 唤醒回调接口
     */
    private class AILocalSignalAndWakeupListenerImpl implements AILocalSignalAndWakeupListener {

        @Override
        public void onInit(int status) {
            if (status == AIConstant.OPT_SUCCESS) {
                mAILocalSignalAndWakeupEngine.start();  // start 唤醒
                Log.d("edong", "唤醒初始化成功");
                Record.feedData();
            } else {
                // 唤醒引擎初始化失败
                Log.d("edong", "唤醒初始化失败");
            }

        }

        @Override
        public void onError(AIError aiError) {
            // 唤醒引擎出错
            Log.d("edong", "唤醒引擎出错");
        }

        @Override
        public void onWakeup(double confidence, String wakeupword) {

            //          LedControl.wakeup(context, WifiUtil.isWifiConnected(context), (int) angle);
            Log.d("edong", "onWakeup");


        }

        @Override
        public void onReadyForSpeech() {
            Log.d("edong", "唤醒引擎准备就绪");
            // 唤醒引擎准备就绪 onReadyForSPeech
        }

        @Override
        public void onRawDataReceived(byte[] bytes, int i) {


        }

        @Override
        public void onResultDataReceived(byte[] bytes, int size, int wakeup_type) {


        }


        @Override
        public void onDoaResult(int doa) {
            Log.d("edong", "onDoaResult");
// 暂停播放 唤醒时取消当前正在 TTS / ASR
            MediaUtil.play(context, ResultBean.PlayType.PAUSE, null, 0);
            mAILocalTtsEngine.stop();
            mAIMixASREngine.cancel();


            mAILocalTtsEngine.speak("我在", "1024");
            // 唤醒后进行语义合成 - - ->需要进行语义理解
            isNeedASR = true;

            LedControl.wakeup(context, WifiUtil.isWifiConnected(context), (int) doa);


        }

    }


    /**
     * 本地语音合成引擎回调
     */
    public class AILocalTTSListenerImpl implements AITTSListener {
        @Override
        public void onInit(int status) {
            if (status == AIConstant.OPT_SUCCESS)
                //本地语音合成初始化成功
                Log.i("edong", "本地语音合成初始化成功");
            else
                //本地语音合成初始化失败
                Log.i("edong", "本地语音合成初始化失败");

        }

        @Override
        public void onError(String s, AIError aiError) {
            LedControl.ttsError(context);
        }

        @Override
        public void onReady(String s) {
            Log.i("edong", "onReady");
            if (!isNeedASR) {
                Log.i("edong", "onReady");
                LedControl.ttsStart(context);
            }
        }

        @Override
        public void onCompletion(String s) {
            Log.i("edong", "onCompletion");
            // 语音合成结束，返回
            if (isNeedASR) {
                // 语音播放的是 "我在"
                mAIMixASREngine.start();
            } else {
                LedControl.ttsEnd(context);
                // 播放 json 解析内容
                if (resultBean == null)
                    return;
                if (resultBean.getPlayType() == ResultBean.PlayType.NO) {
                    // 如果是 NO 可能需要恢复播放
                    if (Constant.IS_WAKEUP_PAUSE_MUSIC)
                        MediaUtil.play(context, ResultBean.PlayType.RESUME, null, 0);
                } else {
                    // 如果是 其他类型
                    MediaUtil.play(context, resultBean.getPlayType(), resultBean.getResList(), resultBean.getPosition());
                }

            }
        }

        @Override
        public void onProgress(int currentTime, int totalTime, boolean isRefTextTTSFinished) {
            //LogUtil.d("currentTime:" + currentTime + "  totalTime:" + totalTime + "  isRefTextTTSFinished:" + isRefTextTTSFinished);
        }


        @Override
        public void onBufferReceived(byte[] bytes) {

        }
    }


    /**
     * 识别引擎回调接口
     */
    private class AIASRListenerImpl implements AIASRListener {

        @Override
        public void onReadyForSpeech() {
            // ("等待用户说话...");
        }

        @Override
        public void onBeginningOfSpeech() {
            //LogUtil.d("用户开始说话");
        }

        @Override
        public void onEndOfSpeech() {
            LedControl.recordend(context);
        }


        @Override
        public void onInit(int status) {
            if (status == 0) {
                // 初始化唤醒引擎
                initWakeupEngine();
            }
        }

        @Override
        public void onError(AIError aiError) {
            // LogUtil.d("识别引擎出错：" + aiError.getError());
            isNeedASR = false;
            resultBean = new ResultBean("", ResultBean.PlayType.PAUSE, null, 0);
            mAILocalTtsEngine.speak("网络好像出了点问题，最好先检查一下网络", "2048");
            //    LedControl.asrError(context);
        }

        @Override
        public void onResults(AIResult aiResult) {

            LedControl.asrEnd(context);

            // json 解析
            resultBean = JsonAnalyze.getResult(aiResult, context);

            // TTS反馈语音识别结果 - - -> 合成结束不进行识别操作
            isNeedASR = false;
            mAILocalTtsEngine.speak(resultBean.getTts(), "2048");

        }

        @Override
        public void onRmsChanged(float v) {
            //  LogUtil.d("ASR 引擎 onRmsChanged float:" + v);
        }

        @Override
        public void onBufferReceived(byte[] bytes, int size) {
            //    LogUtil.d("ASR 引擎 onBufferReceived");

        }
    }


    /**
     * 本地语法资源定制 引擎回调
     */
    private class AILocalGrammarListenerImpl implements AILocalGrammarListener {

        @Override
        public void onInit(int status) {
            if (status == 0) {
                startResGen();
            }
        }

        @Override
        public void onError(AIError aiError) {
            // 语法编译回调出错
        }

        @Override
        public void onUpdateCompleted(String recordId, String path) {
            // 初始化 混合语义引擎
            initMixAsrEngine();
        }
    }

    /**
     * 生成识别资源
     */
    private void startResGen() {
        // 生成 ebnf 语法
        GrammarHelper gh = new GrammarHelper(context);
        String ebnf = gh.importAssets("asr.xbnf");
        // 设置 ebnf 语法
        mAILocalGrammarEngine.setEbnf(ebnf);
        mAILocalGrammarEngine.update();
    }

    public void ttsSpeak(String tts) {

        // 暂停播放 唤醒时取消当前正在 TTS / ASR
        MediaUtil.play(context, ResultBean.PlayType.PAUSE, null, 0);
        mAILocalTtsEngine.stop();
        mAIMixASREngine.cancel();
        mAILocalTtsEngine.speak(tts, "1024");
    }

}
