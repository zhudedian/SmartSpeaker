package com.lingan.edongspeechlibrary.record;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

import com.aispeech.export.engines.AILocalSignalAndWakeupEngine;
import com.lingan.edongspeechlibrary.utils.Constant;
import com.lingan.edongspeechlibrary.utils.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


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
//                FileOutputStream fos = null;
//                final File pcmFile = new File("/mnt/internal_sd/origin.pcm");
//
//                try {
//                    fos = new FileOutputStream(pcmFile, true);
//                } catch (FileNotFoundException e) {
//                    Log.d("edong", e.getMessage());
//                    e.printStackTrace();
//                }
                int minBufferSize = AudioRecord.getMinBufferSize(16000, AudioFormat.CHANNEL_IN_STEREO, AudioFormat.ENCODING_PCM_16BIT);
                AudioRecord audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, 16000, AudioFormat.CHANNEL_IN_STEREO,
                        AudioFormat.ENCODING_PCM_16BIT, minBufferSize * 8);
                byte[] minBuffBytes = new byte[minBufferSize];
                // TODO 保存音频文件

                audioRecord.startRecording();
                while (!Constant.IS_MUTE) {
                    audioRecord.read(minBuffBytes, 0, minBufferSize);

                    mAILocalSignalAndWakeupEngine.start();

                    mAILocalSignalAndWakeupEngine.feedData(minBuffBytes);


//                    try {
//                        fos.write(minBuffBytes, 0, minBufferSize);
//                        fos.flush();
//                    } catch (IOException e) {
//                        Log.d("edong", "写文件异常 " + e.getMessage());
//                        e.printStackTrace();
//                    }


                }

                audioRecord.stop();
                audioRecord.release();
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }).start();

    }

}
