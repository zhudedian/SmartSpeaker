package com.lingan.edongspeechlibrary.utils;

/**
 *
 * Created by dyx on 2017/12/8.
 */

public class Constant {

    // 授权码
    //public static final String APPKEY = "1474602319859602";
    //public static final String SECRETKEY = "66e3cdd7124869d9895ac3b32ee1b364";
    public static final String APPKEY = "151244661445834f";// speech demo
    public static final String SECRETKEY = "f71c24014805dd138b0ad3d0e464a603";// speech demo


    // 禁音标识符
    public static boolean IS_MUTE = false;

    // 唤醒时 是否中断音乐播放
    public static boolean IS_WAKEUP_PAUSE_MUSIC = false;

    // 播放音乐列表第几首歌
    public static int musicPlayPosition = 0;

    // 控制命令反馈
    public static final String COMMAND_OK = "好的";
    public static final String COMMAND_NO_RES = "当前没有正在播放的音乐";
    public static final String COMMAND_VOLUME_ERROR = "音量调节出错";

    // 异常语音反馈
    public static final String DEFAULT_TTS = "没有找到问题答案";
    public static final String JSON_ERROR_TTS = "我还没有学会呢";
    public static final String DOMAIN_IN_PROCESSING = "这个功能我还在学习呢";

}