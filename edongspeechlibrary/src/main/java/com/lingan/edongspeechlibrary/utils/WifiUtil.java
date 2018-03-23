package com.lingan.edongspeechlibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by dyx on 2017/12/11.
 *
 */

public class WifiUtil {

    /**
     * 检查 WIFI 连接状态
     * @param context context
     * @return 网络是否已连接
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetworkInfo.isConnected())
            return true;
        return false;
    }

}
