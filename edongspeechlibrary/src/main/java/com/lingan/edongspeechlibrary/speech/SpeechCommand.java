package com.lingan.edongspeechlibrary.speech;

import com.lingan.edongspeechlibrary.listener.CommandListener;

import org.json.JSONObject;

/**
 * Created by dyx on 2018/1/9.
 * 提供 Command 类 json
 */

public class SpeechCommand {

    private static CommandListener commandListener;

    static void dispatchJson(JSONObject jsonObject){
        if(commandListener==null)
            return;
        commandListener.onCommand(jsonObject);
    }

    public static void startWatching(CommandListener commandListener) {
        SpeechCommand.commandListener = commandListener;
    }

    public static void stopWatching(){
        SpeechCommand.commandListener = null;
    }

}
