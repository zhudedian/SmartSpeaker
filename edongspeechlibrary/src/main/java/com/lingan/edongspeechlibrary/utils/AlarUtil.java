package com.lingan.edongspeechlibrary.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.lingan.edongspeechlibrary.bean.ResultBean;
import com.lingan.edongspeechlibrary.db.AlarEvent;
import com.lingan.edongspeechlibrary.speech.EngineSpeech;
import com.lingan.edongspeechlibrary.speech.SpeechCommand;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Done on 2018/3/27.
 */

public class AlarUtil {

    private static boolean isScaningAlar = false;
    public static void setAlar(Context context,AlarEvent alarEvent){
        String event = alarEvent.getEvent();
        long time = alarEvent.getEvnetTime();
        //时间一到，发送广播（闹钟响了）  
        Intent intent=new Intent();
        intent.putExtra("event",event);
        intent.putExtra("event_time",alarEvent.getTimeStr());
        intent.setAction("com.edong.alarmandnotice.RING");
        //将来时态的跳转  
        PendingIntent pendingIntent=PendingIntent.getBroadcast(context,0x101,intent,0);
        //设置闹钟 
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,time,pendingIntent);
        Log.i("edong","set alar success !");
    }
    public static ResultBean getResultBean(JSONObject jsonObject, Context context) throws JSONException {
        // add for linggan
        ResultBean resultBean = null;
        String tts;

        String timeSpan = "";
        String timeStr = "";
        String dateStr = "";
        String timeSlot = "";
        JSONObject paramObject = jsonObject.getJSONObject("result").getJSONObject("semantics").getJSONObject("request").getJSONObject("param");
        if (paramObject.has("时间间隔")){
            timeSpan = paramObject.getString("时间间隔");
        }
        if (paramObject.has("时间")){
            timeStr = paramObject.getString("时间");
        }
        if (paramObject.has("日期")){
            dateStr = paramObject.getString("日期");
        }
        if (paramObject.has("时间段")){
            timeSlot = paramObject.getString("时间段");
        }
        Log.d("edong", "timeSpan:" + timeSpan+",timeStr:" + timeStr+",dateStr:" + dateStr);
        String event = "";
        if (paramObject.has("事件")){
            event = paramObject.getString("事件");
        }else if (paramObject.has("操作")&&paramObject.getString("操作").equals("显示")){
            tts = findAlarEvent(context);
            resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
            return resultBean;
        }else {
            tts = "好的，去做什么呢";
            resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
            return resultBean;
        }

        SimpleDateFormat formatter = new SimpleDateFormat ("HH:mm:ss");
        SimpleDateFormat format2= new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        SimpleDateFormat format3= new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date date = null;
        Date curDate = new Date(System.currentTimeMillis());
        String curTime = format2.format(curDate);
        String notTimeStr = "";
        try {
            if (!timeStr.equals("")&&dateStr.equals("")){
                notTimeStr = curTime.split(" ")[0]+" "+timeStr;
                date = format2.parse(notTimeStr);
            }else if (timeStr.equals("")&&!dateStr.equals("")){
                if (!timeSlot.equals("")){
                    if (timeSlot.equals("中午")){
                        notTimeStr = dateStr+" "+"12:00:00";
                    }else if (timeSlot.equals("早上")){
                        notTimeStr = dateStr+" "+"08:00:00";
                    }else if (timeSlot.equals("下午")){
                        notTimeStr = dateStr+" "+"16:00:00";
                    }else if (timeSlot.equals("晚上")){
                        notTimeStr = dateStr+" "+"20:00:00";
                    }else {
                        notTimeStr = dateStr+" "+"12:00:00";
                    }
                }else {
                    notTimeStr = dateStr + " " + curTime.split(" ")[1];
                }
                date = format3.parse(notTimeStr);
            }else if (!timeStr.equals("")&&!dateStr.equals("")){
                notTimeStr = dateStr+" "+timeStr;
                date = format3.parse(notTimeStr);
            }else {
                if (!timeSlot.equals("")){
                    if (timeSlot.equals("中午")){
                        notTimeStr = curTime.split(" ")[0]+" "+"12:00:00";
                    }else if (timeSlot.equals("早上")){
                        notTimeStr = curTime.split(" ")[0]+" "+"08:00:00";
                    }else if (timeSlot.equals("下午")){
                        notTimeStr = curTime.split(" ")[0]+" "+"16:00:00";
                    }else if (timeSlot.equals("晚上")){
                        notTimeStr = curTime.split(" ")[0]+" "+"20:00:00";
                    }else {
                        notTimeStr = curTime.split(" ")[0]+" "+"12:00:00";
                    }
                }
            }
            Log.d("edong", "notTimeStr:" + notTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d("edong", "e:" + e.toString());
        }
        String time = format2.format(date);
        Log.d("edong", "time:" + time);

        AlarEvent alarEvent = new AlarEvent(date.getTime(),time,event);


        if (date.getTime()<System.currentTimeMillis()){
            if (!timeSlot.equals("")){
                tts = time.split(" ")[0]+timeSlot+"已经过了哦，请重新设置吧";
            }else {
                tts = time+"已经过了哦，请重新设置吧";
            }
            resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);
            return resultBean;
        }else if (timeSpan.equals("")) {
            if (!timeSlot.equals("")){
                tts = "已设置" + time.split(" ")[0]+timeSlot+time.split(" ")[1] + event+"的提醒";
            }else {
                tts = "已设置" + time + event + "的提醒";
            }
        }else {
            String[] times = timeSpan.split(":");
            int timehours = Integer.parseInt(times[0]);
            int timeMin = Integer.parseInt(times[1]);
            int timeMills = Integer.parseInt(times[2]);
            if(timehours==0){
                if (timeMin == 0){
                    tts = "已设置" + timeMills+"秒后" + event+"的提醒";
                }else if (timeMills == 0){
                    tts = "已设置" + timeMin+"分钟后" + event+"的提醒";
                }else {
                    tts = "已设置" + timeMin+"分钟"+ timeMills+"秒后" + event+"的提醒";
                }

            }else if(timeMin==0){
                if (timeMills == 0){
                    tts = "已设置" + timehours+"小时后" + event+"的提醒";
                }else {
                    tts = "已设置" + timehours+"小时零"+ timeMills+"秒后" + event+"的提醒";
                }
            }else {
                if (timeMills == 0){
                    tts = "已设置" + timehours+"小时"+ timeMin+"分后" + event+"的提醒";
                }else {
                    tts = "已设置" + timehours+"小时"+ timeMin+"分" + timeMills+"秒后"+ event+"的提醒";
                }
            }
        }
        alarEvent.save();

        SharedPreferences preferences = context.getSharedPreferences("speech_prefers", Context.MODE_PRIVATE);
        if (!preferences.getBoolean("data_save_alarevent",false)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("data_save_alarevent", true);
            editor.apply();
        }
        initAlar(context);
        resultBean = new ResultBean(tts, ResultBean.PlayType.NO, null, 0);

        return resultBean;
    }

    public static void initAlar(final Context context) {
        SharedPreferences preferences = context.getSharedPreferences("speech_prefers", Context.MODE_PRIVATE);
        if (!preferences.getBoolean("data_save_alarevent",false)||isScaningAlar) {
            return;
        }
        isScaningAlar = true;
        List<AlarEvent> events = DataSupport.findAll(AlarEvent.class);
        if (events!=null){

            for (AlarEvent event:events){
                Log.i("edong","event.getTimeStr()="+event.getTimeStr()+"event.getEvnetTime()"+event.getEvnetTime()+"System.currentTimeMillis()"+System.currentTimeMillis());

                if (event.getEvnetTime()-System.currentTimeMillis()<1*1000){
                    String eventTime = event.getTimeStr();
                    DataSupport.deleteAll(AlarEvent.class, "timeStr = ?", eventTime);
                    Log.i("edong","时间到了"+event.getTimeStr());
                    EngineSpeech.getInstance(context).ttsSpeak(event.getEvent() + "时间到了，别忘了哦");
                }else if (event.getEvnetTime()-System.currentTimeMillis()<60*1000){
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            context.sendBroadcast(new Intent("com.edong.alarmandnotice.RING"));
                        }
                    },event.getEvnetTime()-System.currentTimeMillis());
                }
            }
        }
        isScaningAlar = false;
    }
    public static int getAlarEventCount(Context context){
        SharedPreferences preferences = context.getSharedPreferences("speech_prefers", Context.MODE_PRIVATE);
        if (!preferences.getBoolean("data_save_alarevent",false)) {
            return 0 ;
        }
        List<AlarEvent> events = DataSupport.findAll(AlarEvent.class);
        if (events!=null){
            return events.size();
        }
        return 0;
    }
    public static String findAlarEvent(Context context){
        String info = "";
        SharedPreferences preferences = context.getSharedPreferences("speech_prefers", Context.MODE_PRIVATE);
        if (!preferences.getBoolean("data_save_alarevent",false)) {
            return "还没有设置提醒，设置一个吧" ;
        }
        List<AlarEvent> events = DataSupport.findAll(AlarEvent.class);
        if (events!=null){
            if (events.size()==0){
                return "还没有设置提醒，设置一个吧" ;
            }else {
                info = "你当前设置了" + events.size() + "个提醒，";
                for (AlarEvent event : events) {
                    info = info + event.getTimeStr() + event.getEvent() + ",";
                }
            }
        }
        return info;
    }
    public static void removeAlar(Context context){
        SharedPreferences preferences = context.getSharedPreferences("speech_prefers", Context.MODE_PRIVATE);
        if (!preferences.getBoolean("data_save_alarevent",false)) {
            return ;
        }
        DataSupport.deleteAll(AlarEvent.class);
    }
}
