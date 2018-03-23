package com.lingan.edongspeechlibrary.record;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import com.aispeech.export.engines.AILocalSignalAndWakeupEngine;
import com.lingan.edongspeechlibrary.utils.Constant;


/**
 * Created by dyx on 2017/12/13.
 * 录音数据采集
 */

public class Record {


    /**
     * 唤醒引擎 FEED 数据
     *
     */
    public static void feedData() {

        final AILocalSignalAndWakeupEngine mAILocalSignalAndWakeupEngine = AILocalSignalAndWakeupEngine.getInstance();

        new Thread(new Runnable() {
            @Override
            public void run() {

                int minBufferSize = AudioRecord.getMinBufferSize(16000, AudioFormat.CHANNEL_IN_STEREO, AudioFormat.ENCODING_PCM_16BIT);
                AudioRecord audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, 16000, AudioFormat.CHANNEL_IN_STEREO,
                        AudioFormat.ENCODING_PCM_16BIT, minBufferSize * 8);
                byte[] minBuffBytes = new byte[minBufferSize];
                audioRecord.startRecording();
                while (!Constant.IS_MUTE) {
                    audioRecord.read(minBuffBytes, 0, minBufferSize);

                    mAILocalSignalAndWakeupEngine.start();

                    mAILocalSignalAndWakeupEngine.feedData(minBuffBytes);


                }

                audioRecord.stop();
                audioRecord.release();

            }
        }).start();

    }

}
