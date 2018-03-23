package com.lingan.edongspeechlibrary.receiver;

import com.lingan.edongspeechlibrary.listener.SmartConfigListener;

/**
 * Created by dyx on 2018/3/9.
 * 提供 SmartConfig 接口 对配网状态进行监听
 */

public class SmartConfig {

    private static SmartConfigListener smartConfigListener;

    public enum SmartConfigState {START, STOP, SUCCESS, FAIL}

    static void dispatchState(SmartConfigState state) {
        if (smartConfigListener == null)
            return;

        switch (state) {
            case START:
                smartConfigListener.onSmartConfigStart();
                break;
            case STOP:
                smartConfigListener.onSmartConfigStop();
                break;
            case SUCCESS:
                smartConfigListener.onSuccess();
                break;
            case FAIL:
                smartConfigListener.onFail();
                break;
            default:
                break;

        }
    }

    public static void startWatching(SmartConfigListener smartConfigListener) {
        SmartConfig.smartConfigListener = smartConfigListener;
    }

    public static void stopWatching() {
        SmartConfig.smartConfigListener = null;
    }

}
