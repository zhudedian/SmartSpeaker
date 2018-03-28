package com.lingan.edongspeechlibrary.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.lingan.edongspeechlibrary.db.AlarEvent;
import com.lingan.edongspeechlibrary.speech.EngineSpeech;
import com.lingan.edongspeechlibrary.utils.AlarUtil;

import org.litepal.crud.DataSupport;

public class AlarReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        if (intent.getAction().equals(Intent.ACTION_TIME_TICK)){
//            Log.i("edong","过了一分钟！");
        AlarUtil.initAlar(context);
//        }else {
//
//            Log.i("edong", "闹钟时间到！");
//            String eventTime = intent.getStringExtra("event_time");
//            DataSupport.deleteAll(AlarEvent.class, "timeStr = ?", eventTime);
//            EngineSpeech.getInstance(context).ttsSpeak(intent.getStringExtra("event") + "时间到了，别忘了哦");
//        }
    }
}
