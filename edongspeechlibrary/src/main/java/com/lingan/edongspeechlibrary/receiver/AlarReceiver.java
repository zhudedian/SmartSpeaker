package com.lingan.edongspeechlibrary.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.lingan.edongspeechlibrary.speech.EngineSpeech;

public class AlarReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("edong","闹钟时间到！");
        EngineSpeech.getInstance(context).ttsSpeak(intent.getStringExtra("event")+"时间到了，别忘了哦");
    }
}
