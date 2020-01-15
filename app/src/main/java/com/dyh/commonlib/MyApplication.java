package com.dyh.commonlib;

import android.content.Intent;
import android.support.multidex.MultiDex;

import com.dyh.commonlib.entity.local.SPEntity;
import com.dyh.commonlib.http.HttpHostUtil;
import com.dyh.commonlib.http.MyHeaderTokenInterceptor;
import com.dyh.commonlib.ui.LoginActivity;
import com.dyh.common.lib.BaseApplication;
import com.dyh.common.lib.dw.util.SystemInfoUtils;
import com.dyh.common.lib.easy.EasyLog;
import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.model.HttpHeaders;

import cn.vip.dw.bluetoothprinterlib.BluetoothPrintManager;


/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/18/018 15:28
 * 描述：
 */
public class MyApplication extends BaseApplication {
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        MultiDex.install(this);

        //是否启用日志
        EasyLog.Companion.getDEFAULT().setEnable(true);

        //刷新打印机缓存
        BluetoothPrintManager.getInstance().resetCacheBoundPrinterInfo(this);

        initHttp();
    }

    @Override
    public void reLogin() {
        SPEntity spEntity = EasySharedPreferences.load(SPEntity.class);
        spEntity.loginResponseBean = null;
        spEntity.commit();
        AppExit();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void initHttp() {
        //设置请求头
        HttpHeaders headers = getCommonHttpHeaders();
        //设置请求参数
        EasyHttp.getInstance()
                .debug("GouKu", BuildConfig.DEBUG)
                .addCommonHeaders(headers)//设置全局公共头
                .addInterceptor(new MyHeaderTokenInterceptor(headers));//token追加拦截器

    }

    public HttpHeaders getCommonHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("User-Agent", SystemInfoUtils.getUserAgent(this));
        headers.put("Gouku-App-Origin", "31");
        headers.put("Content-Type", "application/json;charset=UTF-8");
        return headers;
    }

    public static MyApplication getInstance() {
        return myApplication;
    }

}
