package com.lingan.edongspeechlibrary.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.lingan.edongspeechlibrary.db.AlarEvent;

/**
 * Created by Done on 2018/3/27.
 */

public class AlarUtil {

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
    }
}
