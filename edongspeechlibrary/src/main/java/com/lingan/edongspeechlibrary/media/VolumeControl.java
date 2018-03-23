package com.lingan.edongspeechlibrary.media;

import android.content.Context;
import android.media.AudioManager;


/**
 * Created by dyx on 2017/9/19.
 * 对设备音量的控制
 */

public class VolumeControl {

    public static void up(Context context) {
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, 0);
    }

    public static void down(Context context) {
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, 0);
    }

    public static void max(Context context) {
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
    }

    public static void min(Context context) {

        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
    }

    public static void num(Context context, int num) {
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        if (num > max)
            num = max;
/*        int volumeNum = num * 10 / max;
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volumeNum, 0);*/
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, num, 0);
    }

}
