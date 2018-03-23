package com.lingan.edongspeechlibrary.listener;

import org.json.JSONObject;

/**
 * Created by dyx on 2018/1/9.
 */

public interface CommandListener {
    void onCommand(JSONObject jsonObject);
}