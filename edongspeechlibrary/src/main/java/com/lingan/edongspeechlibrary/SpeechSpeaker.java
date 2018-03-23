package com.lingan.edongspeechlibrary;

import android.content.Context;
import android.content.IntentFilter;

import com.lingan.edongspeechlibrary.bean.ResultBean;
import com.lingan.edongspeechlibrary.led.LedControl;
import com.lingan.edongspeechlibrary.media.MediaUtil;
import com.lingan.edongspeechlibrary.receiver.FunctionBroadcastReceiver;
import com.lingan.edongspeechlibrary.speech.SpeechAuth;
import com.lingan.edongspeechlibrary.utils.WifiUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dyx on 2017/12/8.
 * 开机完成后 启动 activity
 */

public class SpeechSpeaker {


    public static void init(Context context) {

        final Context mContext = context.getApplicationContext();


        // 按键监听
        FunctionBroadcastReceiver functionBroadcastReceiver = new FunctionBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.edong.mute");
        intentFilter.addAction("com.edong.pause");
        intentFilter.addAction("com.edong.quit_smart_config");
        intentFilter.addAction("com.edong.start_smart_config");
        // add 上一曲 下一曲
        intentFilter.addAction("com.edong.next");
        intentFilter.addAction("com.edong.previous");

        mContext.registerReceiver(functionBroadcastReceiver,intentFilter);


        // APP 启动完成，检测 wifi是否已经连接了,并对用户进行语音提示
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int count = 0;
            List resList = new ArrayList();
            @Override
            public void run() {
                boolean wifiConnected = WifiUtil.isWifiConnected(mContext);
                if (wifiConnected) {
                    //  提示开机连网成功
                    LedControl.appStarted(mContext,true);
                    resList.add(0,R.raw.wifi_connected_success);
                    MediaUtil.play(mContext, ResultBean.PlayType.TIP, resList, 0);
                    SpeechAuth.doAuth(mContext);
                    this.cancel();
                }
                if (++count == 10) {
                    // 提示用户网络连接失败
                    LedControl.appStarted(mContext,false);
                    resList.add(0,R.raw.wifi_connected_fail);
                    MediaUtil.play(mContext, ResultBean.PlayType.TIP, resList, 0);
                    SpeechAuth.doAuth(mContext);
                    this.cancel();
                }
            }
        }, 500, 2000);

    }



}