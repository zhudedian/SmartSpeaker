package com.lingan.edongspeechlibrary.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.lingan.edongspeechlibrary.SpeechApplication;

public class AlarService extends Service {
    public AlarService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate(){
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        SpeechApplication.getContext().registerReceiver(alarReceiver,intentFilter);
    }
    @Override
    public int onStartCommand(Intent intent,int flags, int startId){




        return super.onStartCommand(intent,flags,startId);
    }
    BroadcastReceiver alarReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };
    @Override
    public void onDestroy(){
        SpeechApplication.getContext().unregisterReceiver(alarReceiver);

        super.onDestroy();
    }
}
