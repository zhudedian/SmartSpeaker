package com.lingan.edongspeechlibrary.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;


import com.lingan.edongspeechlibrary.R;
import com.lingan.edongspeechlibrary.bean.ResultBean;
import com.lingan.edongspeechlibrary.led.LedControl;
import com.lingan.edongspeechlibrary.media.MediaUtil;
import com.lingan.edongspeechlibrary.record.Record;
import com.lingan.edongspeechlibrary.speech.JsonAnalyze;
import com.lingan.edongspeechlibrary.speech.SpeechAuth;
import com.lingan.edongspeechlibrary.utils.Constant;
import com.lingan.edongspeechlibrary.utils.WifiUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by dyx on 2017/12/14.
 * 接收系统 禁音、暂停、开机广播
 */
public class FunctionBroadcastReceiver extends BroadcastReceiver {


    Context context;
    List resList = new ArrayList();
    MyTimerTask myTimerTask;
    Boolean isTimmerRunning = false;


    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;

        if (intent.getAction().equals("com.edong.mute")) {
            Constant.IS_MUTE = !Constant.IS_MUTE;
            if (!Constant.IS_MUTE)
                Record.feedData();

        } else if (intent.getAction().equals("com.edong.pause")) {
            MediaUtil.pauseResume();
        } else if (intent.getAction().equals("com.edong.next")) {
            // 下一曲
            if (!MediaUtil.isPlaying())
                return;
            if (Constant.musicPlayPosition == resList.size() - 1)
                Constant.musicPlayPosition = -1;
            MediaUtil.play(context.getApplicationContext(), ResultBean.PlayType.MUSIC, JsonAnalyze.resList, ++Constant.musicPlayPosition);

        } else if (intent.getAction().equals("com.edong.previous")) {
            // 上一曲
            if (!MediaUtil.isPlaying())
                return;
            if (Constant.musicPlayPosition == 0)
                Constant.musicPlayPosition = 1;
            MediaUtil.play(context.getApplicationContext(), ResultBean.PlayType.MUSIC, JsonAnalyze.resList, --Constant.musicPlayPosition);

        } else if (intent.getAction().equals("com.edong.start_smart_config")) {

            SmartConfig.dispatchState(SmartConfig.SmartConfigState.START);

            // 播放语音提示用户 - - - 进入联网模式
            resList.add(0, R.raw.enter_wifi_mode);
            MediaUtil.play(context, ResultBean.PlayType.TIP, resList, 0);

            myTimerTask = new MyTimerTask();
            Timer timer = new Timer();
            timer.schedule(myTimerTask, 200, 2000);
            isTimmerRunning = true;

        } else if (intent.getAction().equals("com.edong.quit_smart_config")) {
            if (isTimmerRunning) {

                SmartConfig.dispatchState(SmartConfig.SmartConfigState.STOP);

                // 如果当前TimerTask正在运行
                // 播放语音提示 - - - 退出联网模式
                resList.add(0, R.raw.exit_wifi_mode);
                MediaUtil.play(context, ResultBean.PlayType.TIP, resList, 0);

                LedControl.smartConfigFinish(context, false);
                myTimerTask.cancel();
                isTimmerRunning = false;
            }
        }
    }


    class MyTimerTask extends TimerTask {
        int count = 0;

        @Override
        public void run() {
            boolean wifiConnected = WifiUtil.isWifiConnected(context);
            if (wifiConnected) {

                SmartConfig.dispatchState(SmartConfig.SmartConfigState.SUCCESS);

                // 语音提示配网成功
                resList.add(0, R.raw.wifi_connected_success);
                MediaUtil.play(context, ResultBean.PlayType.TIP, resList, 0);

                LedControl.smartConfigFinish(context, true);

                // 配网成功 执行授权操作
                SpeechAuth.doAuth(context.getApplicationContext());
                this.cancel();
                isTimmerRunning = false;
            }
            if (++count == 80) {

                SmartConfig.dispatchState(SmartConfig.SmartConfigState.FAIL);

                // 语音提示配网失败
                resList.add(0, R.raw.wifi_connected_fail);
                MediaUtil.play(context, ResultBean.PlayType.TIP, resList, 0);

                LedControl.smartConfigFinish(context, false);

                this.cancel();
                isTimmerRunning = false;
            }

        }
    }


}
