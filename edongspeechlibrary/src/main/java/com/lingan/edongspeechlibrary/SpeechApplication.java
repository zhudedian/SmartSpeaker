package com.lingan.edongspeechlibrary;

import android.app.Application;

import com.aispeech.common.AIConstant;

/**
 * Created by dyx on 2017/12/13.
 * Application 对思必驰的一些配置初始化
 */

public class SpeechApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AIConstant.openLog();//打开日志，默认关闭日志
        //AIConstant.setAudioRecorderType(AIConstant.TYPE_CPLD);   //使用CPLD
        // AIConstant.setAudioRecorderType(AIConstant.TYPE_COMMON_CIRCLE);  //环形麦
        AIConstant.setAudioRecorderType(AIConstant.TYPE_SPI); //设置使用spi方式接收音频数据
    }
}
