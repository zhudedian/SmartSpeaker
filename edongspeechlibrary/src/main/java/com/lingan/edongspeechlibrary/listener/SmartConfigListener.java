package com.lingan.edongspeechlibrary.listener;

/**
 * Created by dyx on 2018/3/9.
 * 配网状态监听
 */

public interface SmartConfigListener {

    // 进入配网模式
    void onSmartConfigStart();
    // 退出配网模式
    void onSmartConfigStop();
    // 配网成功
    void onSuccess();
    // 配网失败
    void onFail();

}
