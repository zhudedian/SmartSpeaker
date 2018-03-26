package com.lingan.edongspeechlibrary.speech;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.aispeech.AIResult;

import com.google.gson.Gson;
import com.lingan.edongspeechlibrary.bean.MusicBean;

import com.lingan.edongspeechlibrary.bean.NetFM3Bean;
import com.lingan.edongspeechlibrary.bean.NetFMBean;
import com.lingan.edongspeechlibrary.bean.NewsBean;
import com.lingan.edongspeechlibrary.bean.ResultBean;
import com.lingan.edongspeechlibrary.db.AlarEvent;
import com.lingan.edongspeechlibrary.media.MediaUtil;
import com.lingan.edongspeechlibrary.media.VolumeControl;
import com.lingan.edongspeechlibrary.utils.Constant;
import com.lingan.edongspeechlibrary.utils.FileUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by dyx on 2017/12/22.
 * 对思必驰返回 json 进行解析
 */

public class JsonAnalyze {


    public static List resList;

    /**
     * json 结果解析
     *
     * @param aiResult 思必驰语音识别结果
     * @return 需要tts合成的文本
     */
    public static ResultBean getResult(AIResult aiResult, Context context) {

        ResultBean resultBean = null;

        String tts = Constant.DEFAULT_TTS;
        String jsonString = aiResult.getResultObject().toString();

        Log.d("edong", "json:" + jsonString);

        FileUtil.saveFile(jsonString);

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            // 获取 result - - > semantics - -> request - -> domain
            String resultDomain = jsonObject.getJSONObject("result").getJSONObject("sds").getString("domain");

            Log.d("edong", "resultDomain:" + resultDomain);
            switch (resultDomain) {

                // 直接 TTS 反馈结果
                case "calendar":
                case "calculator":
                case "poetry":
                case "weather":
                case "stock":   // 股票
                    tts = jsonObject.getJSONObject("result").getJSONObject("sds").getString("output");
                    resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
                    break;

                case "reminder":
                    resultBean = getReminderResult(jsonObject, context);
                    break;
                case "chat":
                    JSONObject paramObject;
                    JSONObject resultObject = jsonObject.getJSONObject("result");
                    if (resultObject.has("semantics")) {
                        paramObject = jsonObject.getJSONObject("result").getJSONObject("semantics").getJSONObject("request").getJSONObject("param");
                        Log.d("edong", "chat2:");
                        if (paramObject.has("操作") && paramObject.has("对象")) {
                            String operation = paramObject.getString("操作");
                            String obj = paramObject.getString("对象");
                            if ((operation.equals("收听") || operation.equals("说")) && obj.equals("笑话")) {
                                String urlStr = jsonObject.getJSONObject("result").getJSONObject("sds").getJSONObject("data").getJSONArray("dbdata").get(0).toString();
                                JSONObject urlObject = new JSONObject(urlStr);
                                String url = urlObject.getString("url");
                                //Log.i("edong", "url=" + url);
                                MediaUtil.sayJoke(url);
                                tts = "";
                            } else {
                                tts = "请再说一遍";
                            }
                        }
                    }else {
                        JSONObject outputObject = jsonObject.getJSONObject("result").getJSONObject("sds").getJSONObject("log").getJSONObject("output");
                        if (outputObject.has("nlg")){
                            tts = outputObject.getString("nlg");
                        }else {
                            tts = "请再说一遍";
                        }
                    }
                    //Log.d("edong", "tts:" + tts);
                    resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
                    break;
                // 音乐播放
                case "music":
                    paramObject = jsonObject.getJSONObject("result").getJSONObject("semantics").getJSONObject("request").getJSONObject("param");
                    String input = jsonObject.getJSONObject("result").getString("input");
                    if (input.equals("我想听")||input.equals("听音乐")||input.equals("我想听音乐")){
                        tts = "请问你要听什么歌";
                        resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
                        break;
                    }
                    if (paramObject.has("__tgt__")){
                        String tgt = paramObject.getString("__tgt__");
                        if (tgt.equals("歌手名")) {
                            tts = MediaUtil.getPlayingMusicArtist();
                            Log.i("edong", "tts" + tts);
                            resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
                            break;
                        }else if (tgt.equals("歌曲名")) {
                            tts = "正在播放的是"+MediaUtil.getPlayingName();
                            Log.i("edong", "tts" + tts);
                            resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
                            break;
                        }
                    }else if (paramObject.has("重复次数")){
                        String timesStr = paramObject.getString("重复次数");
                        MediaUtil.singleTimes = Integer.parseInt(timesStr);
                        MediaUtil.removeSinglePlay();
                        tts = "已设定重复播放"+MediaUtil.singleTimes+"次";
                        resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
                        break;
                    }else if (paramObject.has("功能类型")){
                        String function = paramObject.getString("功能类型");
                        if (function.equals("新手引导")) {
                            tts = "这个问题很简单，您只需要跟我说播放音乐，或者跟我说具体播放什么音乐就可以了，赶快试试吧";
                            resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
                            break;
                        }
                    }
                    JSONObject musicDataObject = jsonObject.getJSONObject("result").getJSONObject("sds").getJSONObject("data");
                    Gson musicGson = new Gson();
                    List<MusicBean.DbdataBean> musicList = musicGson.fromJson(musicDataObject.toString(), MusicBean.class).getDbdata();

                    resList = musicList;
                    Constant.musicPlayPosition = 0;

                    if (musicList.size() == 0) {
                        tts = "我还没有找到这首歌，试试听点别的吧";
                        resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
                    } else {
                        tts = musicList.get(0).getArtist() + " " + musicList.get(0).getTitle();
                        resultBean = new ResultBean(tts, ResultBean.PlayType.MUSIC, musicList, 0);
                    }
                    break;

                // 电台 笑话、故事、电子书
                case "netfm":
                    JSONObject netfmDataObject = jsonObject.getJSONObject("result").getJSONObject("sds").getJSONObject("data");
                    String slotcount = jsonObject.getJSONObject("result").getJSONObject("semantics").getJSONObject("request").getString("slotcount");
                    String domain = jsonObject.getJSONObject("result").getJSONObject("semantics").getJSONObject("request").getString("domain");
                    paramObject = jsonObject.getJSONObject("result").getJSONObject("semantics").getJSONObject("request").getJSONObject("param");
                    Log.i("edong","slotcount="+slotcount);
                    if (!paramObject.has("故事名")&&!paramObject.has("keyword")&&
                            paramObject.has("操作")&&paramObject.has("对象")){
                        tts = "请说您要收听的内容";
                        resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
                        break;
                    }else if (paramObject.has("__tgt__")&&paramObject.has("对象")){
                        String tgt = paramObject.getString("__tgt__");
                        String obj = paramObject.getString("对象");
                        if (tgt.equals("故事名")&&obj.equals("故事")){
                            if (MediaUtil.getPlayingName().equals("")){
                                tts = "当前没有播放的故事";
                                resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
                            }else {
                                tts = "当前正在播放的故事是"+MediaUtil.getPlayingName();
                                resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
                            }
                            break;
                        }
                    }
                    if (slotcount.equals("3")&&!domain.equals("故事")){
                        NetFM3Bean netFM3Bean = null;
                        List<NetFM3Bean.ResultBean.SdsBean.DataBean.DbdataBean> netfm3List = null;
                        try {
                            netFM3Bean = new Gson().fromJson(jsonObject.toString(),NetFM3Bean.class);
                            netfm3List = netFM3Bean.getResult().getSds().getData().getDbdata();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        if (netfm3List == null || netfm3List.size() == 0) {
                            tts = "我还没有找到这个，试试听点别的吧";
                            resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
                        } else {
                            tts = netfm3List.get(0).getRadio_name();
                            resultBean = new ResultBean(tts, ResultBean.PlayType.NETFM3, netfm3List, 0);
                            break;
                        }

                    }
                    Gson netfmGson = new Gson();
                    List<NetFMBean.DbdataBean> netfmList = netfmGson.fromJson(netfmDataObject.toString(), NetFMBean.class).getDbdata();
                    if (netfmList == null || netfmList.size() == 0) {
                        tts = "我还没有找到这个，试试听点别的吧";
                        resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
                    } else {
                        tts = netfmList.get(0).getTrack();
                        resultBean = new ResultBean(tts, ResultBean.PlayType.NETFM, netfmList, 0);
                    }
                    break;

                case "news":
                    // 播放新闻
                    JSONObject newsDataObject = jsonObject.getJSONObject("result").getJSONObject("sds").getJSONObject("data");
                    Gson newsGson = new Gson();
                    List<NewsBean.DbdataBean> newsList = newsGson.fromJson(newsDataObject.toString(), NewsBean.class).getDbdata();
                    tts = jsonObject.getJSONObject("result").getJSONObject("sds").getString("output");
                    if (newsList.size() == 0)
                        resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
                    else
                        resultBean = new ResultBean(tts, ResultBean.PlayType.NEWS, newsList, 0);
                    break;

                case "command":
                    // 命令  家电控制、播放控制
                    resultBean = getCommondResult(jsonObject, context);
                    break;
                default:
                    resultBean = new ResultBean(Constant.DEFAULT_TTS, ResultBean.PlayType.NO, null, 0);
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean(Constant.JSON_ERROR_TTS, ResultBean.PlayType.NO, null, 0);
        }
        return resultBean;
    }


    // Commond
    private static ResultBean getCommondResult(JSONObject jsonObject, Context context) throws JSONException {

        // add for linggan
        SpeechCommand.dispatchJson(jsonObject);


        ResultBean resultBean = null;
        String tts;
        Log.i("edong", "tts1");
        JSONObject sdsObject = jsonObject.getJSONObject("result").getJSONObject("sds");
        JSONObject semanticsObject = jsonObject.getJSONObject("result").getJSONObject("semantics");
        JSONObject paramObject = jsonObject.getJSONObject("result").getJSONObject("semantics").getJSONObject("request").getJSONObject("param");
        JSONObject nbestparamObject = null ;
        if (semanticsObject.has("nbest")){
            nbestparamObject = ((JSONObject) jsonObject.getJSONObject("result").getJSONObject("semantics")
                    .getJSONArray("nbest").get(0)).getJSONObject("request").getJSONObject("param");
        }
        if (paramObject.has("__tgt__")||(nbestparamObject!=null&&nbestparamObject.has("__tgt__"))){
            String tgt = "";
            if (paramObject.has("__tgt__")){
                tgt = paramObject.getString("__tgt__");
            }else if (nbestparamObject!=null){
                tgt = nbestparamObject.getString("__tgt__");
            }
            if (tgt.equals("歌手名")) {
                tts = MediaUtil.getPlayingMusicArtist();
                Log.i("edong", "tts" + tts);
                resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
                return resultBean;
            }else if (tgt.equals("歌曲名")) {
                tts = "正在播放的是"+MediaUtil.getPlayingName();
                Log.i("edong", "tts" + tts);
                resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
                return resultBean;
            }else if (tgt.equals("播放内容")) {
                tts = "现在播放的是"+MediaUtil.getPlayingName();
                Log.i("edong", "tts" + tts);
                resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
                return resultBean;
            }
        }
        if (sdsObject.has("dev_name")) {
            // 设备控制
            tts = Constant.COMMAND_OK;
            resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);

        } else if (sdsObject.has("object")) {
            // 其他各类 音乐控制 ...
            String commandType = sdsObject.getString("object");

            // 目前命令类支持 音乐 上一首，下一首，暂停，继续
            if (commandType.equals("音乐")) {
                // 控制命令是音乐类型
                if (resList == null || resList.size() == 0) {
                    tts = Constant.COMMAND_NO_RES;
                    resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
                } else {
                    String operation = jsonObject.getJSONObject("result").getJSONObject("sds").getString("operation");
                    switch (operation) {
                        case "暂停":
                            tts = Constant.COMMAND_OK;
                            resultBean = new ResultBean(tts, ResultBean.PlayType.PAUSE, null, Constant.musicPlayPosition);
                            break;
                        case "上一首":
                            MediaUtil.removeSinglePlay();
                            if (Constant.musicPlayPosition == 0)
                                Constant.musicPlayPosition = 1;
                            //resultBean = new ResultBean(Constant.COMMAND_OK, ResultBean.PlayType.MUSIC, resList, --musicPlayPosition);
                            tts = ((MusicBean.DbdataBean) resList.get(--Constant.musicPlayPosition)).getArtist() + ((MusicBean.DbdataBean) resList.get(Constant.musicPlayPosition)).getTitle();
                            resultBean = new ResultBean(tts, ResultBean.PlayType.MUSIC, resList, Constant.musicPlayPosition);
                            break;
                        case "下一首":
                            MediaUtil.removeSinglePlay();
                            if (Constant.musicPlayPosition == resList.size() - 1)
                                Constant.musicPlayPosition = -1;
                            //resultBean = new ResultBean(Constant.COMMAND_OK, ResultBean.PlayType.MUSIC, resList, ++musicPlayPosition);
                            tts = ((MusicBean.DbdataBean) resList.get(++Constant.musicPlayPosition)).getArtist() + ((MusicBean.DbdataBean) resList.get(Constant.musicPlayPosition)).getTitle();
                            resultBean = new ResultBean(tts, ResultBean.PlayType.MUSIC, resList, Constant.musicPlayPosition);
                            break;
                        case "继续":
                            resultBean = new ResultBean(Constant.COMMAND_OK, ResultBean.PlayType.RESUME, resList, Constant.musicPlayPosition);
                            break;
                        case "随机播放":
                            MediaUtil.removeSinglePlay();
                            Random random = new Random();
                            Constant.musicPlayPosition = random.nextInt(resList.size());
                            //resultBean = new ResultBean(Constant.COMMAND_OK, ResultBean.PlayType.MUSIC, resList, ++musicPlayPosition);
                            tts = ((MusicBean.DbdataBean) resList.get(Constant.musicPlayPosition)).getArtist() + ((MusicBean.DbdataBean) resList.get(Constant.musicPlayPosition)).getTitle();
                            resultBean = new ResultBean(tts, ResultBean.PlayType.MUSIC, resList, Constant.musicPlayPosition);
                            break;
                        case "单曲循环":
                            MediaUtil.setSinglePlay();
                            tts = "已设置为单曲循环";
                            resultBean = new ResultBean(tts, ResultBean.PlayType.RESUME, resList, Constant.musicPlayPosition);
                            break;
                        default:
                            tts = Constant.DOMAIN_IN_PROCESSING;
                            resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
                            break;
                    }
                }
            } else if (commandType.equals("音量")) {
                tts = Constant.COMMAND_OK;
                String operation = jsonObject.getJSONObject("result").getJSONObject("sds").getString("operation");
                if (operation.equals("+")) {
                    VolumeControl.up(context);
                } else if (operation.equals("-")) {
                    VolumeControl.down(context);
                } else if (operation.equals("max")) {
                    VolumeControl.max(context);
                } else if (operation.equals("min")) {
                    VolumeControl.min(context);
                } else {
                    try {
                        int volumeNum = Integer.parseInt(operation);
                        VolumeControl.num(context, volumeNum);
                    } catch (Exception e) {
                        tts = Constant.COMMAND_VOLUME_ERROR;
                    }
                }
                resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
            } else if (commandType.equals("进度")) {
                tts = Constant.COMMAND_OK;
                int position = 0;
                try {
                    String posStr = jsonObject.getJSONObject("result").getJSONObject("semantics").getJSONObject("request").getJSONObject("param").getString("进度");
                    if(posStr.equals("+")){
                        position = 30;
                    }else if (posStr.equals("-")){
                        position = -30;
                    }else {
                        String[] times = {};
                        if (posStr.contains("+")){
                            times = posStr.replace("+", "").split(":");
                            position = Integer.parseInt(times[0]) * 60 * 60 + Integer.parseInt(times[1]) * 60 + Integer.parseInt(times[2]);
                        }else if (posStr.contains("-")){
                            times = posStr.replace("-", "").split(":");
                            position = Integer.parseInt(times[0]) * 60 * 60 + Integer.parseInt(times[1]) * 60 + Integer.parseInt(times[2]);
                            position*=-1;
                        }

                    }
                    Log.i("edong","position"+position);
                }catch (Exception e){
                    e.printStackTrace();
                }
                MediaUtil.fastMills = position*1000;
                resultBean = new ResultBean(tts, ResultBean.PlayType.FAST, null, 0);
            } else {
                // 控制命令是非音乐类型
                tts = Constant.DOMAIN_IN_PROCESSING;
                resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
            }


        } else {
            // ...
            tts = Constant.DOMAIN_IN_PROCESSING;
            resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
        }

        return resultBean;

    }
    // reminder
    private static ResultBean getReminderResult(JSONObject jsonObject, Context context) throws JSONException {
        // add for linggan
        SpeechCommand.dispatchJson(jsonObject);
        ResultBean resultBean = null;
        String tts;

        JSONObject paramObject = jsonObject.getJSONObject("result").getJSONObject("semantics").getJSONObject("request").getJSONObject("param");
        String event = paramObject.getString("事件");
        String timeStr = paramObject.getString("时间");
        SimpleDateFormat formatter = new SimpleDateFormat ("HH:mm:ss");
        SimpleDateFormat format2= new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date curDate = new Date(System.currentTimeMillis());
        String curTime = format2.format(curDate);
        String notTimeStr = curTime.split(" ")[0]+" "+timeStr;
        try {
            date = format2.parse(notTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String time = format2.format(date);
        Log.d("edong", "time:" + time);
        //时间一到，发送广播（闹钟响了）  
        Intent intent=new Intent();
        intent.putExtra("event",event);
        intent.setAction("com.edong.alarmandnotice.RING");
        //将来时态的跳转  
        PendingIntent pendingIntent=PendingIntent.getBroadcast(context,0x101,intent,0);
        //设置闹钟 
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,date.getTime(),pendingIntent);
        AlarEvent alarEvent = new AlarEvent(date.getTime(),time,event);
        alarEvent.save();
        tts = "已设置"+time+"的提醒";
        resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);

        return resultBean;
    }
}
