package com.lingan.edongspeechlibrary.speech;

import android.content.Context;
import android.util.Log;

import com.aispeech.export.listeners.AIAuthListener;
import com.aispeech.speech.AIAuthEngine;
import com.lingan.edongspeechlibrary.utils.Constant;

import java.io.FileNotFoundException;

/**
 * Created by dyx on 2017/12/11.
 *  思必驰授权操作
 *  授权成功 自动执行 初始化引擎操作
 */

public class SpeechAuth {

    private static AIAuthEngine mAIAuthEngine;

    public static void doAuth(final Context context){

        mAIAuthEngine = AIAuthEngine.getInstance(context.getApplicationContext());

        try {
            mAIAuthEngine.init(Constant.APPKEY,Constant.SECRETKEY,"");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        mAIAuthEngine.setOnAuthListener(new AIAuthListener() {
            @Override
            public void onAuthSuccess() {
                // 授权成功
                // 初始化引擎
                Log.i("edong","onAuthSuccess");
                EngineSpeech.getInstance(context).initTtsGrammarEngine();

            }

            @Override
            public void onAuthFailed(String s) {
                // 授权失败
                Log.i("edong","onAuthFailed,"+s);
            }
        });

        if(mAIAuthEngine.isAuthed()){
            // 已授权
            // 初始化引擎
            Log.i("edong","mAIAuthEngine.isAuthed()");
            EngineSpeech.getInstance(context).initTtsGrammarEngine();

        }else{
            // 未授权
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 授权中
                    final boolean authRestult = mAIAuthEngine.doAuth();
/*                    if(authRestult)
                        LogUtil.d("authRestult true");
                    else
                        LogUtil.d("authRestult false");*/
                }
            }).start();

        }

    }

}
