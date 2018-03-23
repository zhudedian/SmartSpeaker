package com.lingan.edongspeechlibrary.led;

import android.content.Context;
import android.content.Intent;

/**
 * Created by dyx on 2017/12/8.
 *
 * 发送广播形式 对LED提示灯进行控制
 */

public class LedControl {

    /**
     * app 启动完成发送的广播 灭灯
     * @param context context
     * @param isWifiConnected  网络是否连接成功
     */
    public static void appStarted(Context context, boolean isWifiConnected){
        Intent intent = new Intent();
        intent.setAction("com.edong.appstarted");
        intent.putExtra("WIFI_STATE", isWifiConnected);
        context.sendBroadcast(intent);
    }

    /**
     * 进入配网模式 配网结束后 发送网络连接 成功/失败的广播 灭灯
     * @param context context
     * @param isWifiConnected   网络状态
     */
    public static void smartConfigFinish(Context context,boolean isWifiConnected){
        Intent wifi_intent = new Intent();
        wifi_intent.setAction("com.edong.wifistate");
        wifi_intent.putExtra("WIFI_STATE", isWifiConnected);
        context.sendBroadcast(wifi_intent);
    }

    /**
     * 设备被唤醒
     * @param context context
     * @param angle 唤醒时的角度
     */
    public static void wakeup(Context context,boolean isWifiConnected,int angle) {
        Intent intent = new Intent();
        intent.setAction("com.edong.wakeup");
        intent.putExtra("WIFI_STATE", isWifiConnected);
        intent.putExtra("ANGLE", angle);
        context.sendBroadcast(intent);
    }


    /**
     * 唤醒后 录音结束
     * @param context context
     */
    public static void recordend(Context context){
        Intent intent = new Intent();
        intent.setAction("com.edong.recordend");
        context.sendBroadcast(intent);
    }

    /**
     * 语义理解结束
     * @param context context
     */
    public static void asrEnd(Context context){
        Intent intent = new Intent();
        intent.setAction("com.edong.processend");
        context.sendBroadcast(intent);
    }

    /**
     * 语义理解出错
     * @param context context
     */
    public static void asrError(Context context){
        Intent intent = new Intent();
        intent.setAction("com.edong.recorderror");
        context.sendBroadcast(intent);
    }


    /**
     * 开始语音合成
     * @param context context
     */
    public static void ttsStart(Context context){
        Intent intent = new Intent();
        intent.setAction("com.edong.speakbegin");
        context.sendBroadcast(intent);
    }

    /**
     * 语音合成结束
     * @param context context
     */
    public static void ttsEnd(Context context){
        Intent intent = new Intent();
        intent.setAction("com.edong.speakend");
        context.sendBroadcast(intent);
    }

    /**
     * 语音合成出错
     * @param context context
     */
    public static void ttsError(Context context){
        Intent intent = new Intent();
        intent.setAction("com.edong.speakerror");
        context.sendBroadcast(intent);
    }


}
