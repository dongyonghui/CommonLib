package com.dyh.android.winehall;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;

import com.dyh.android.winehall.entity.local.SPAuthEntity;
import com.dyh.android.winehall.entity.local.SPUserEntity;
import com.dyh.android.winehall.http.HttpHostUtil;
import com.dyh.android.winehall.http.MyHeaderTokenInterceptor;
import com.dyh.android.winehall.ui.mine.LoginActivity;
import com.dyh.common.lib.BaseApplication;
import com.dyh.common.lib.easy.EasyLog;
import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.model.HttpHeaders;
import com.dyh.common.lib.weigit.titlebar.statusbar.StatusBarUtils;

import java.io.File;

/**
 * 作者：Allan
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/18/018 15:28
 * 描述：
 */
public class MyApplication extends BaseApplication {
    private static MyApplication myApplication;
    private int mStatusBarHeight;//状态栏高度

    private final Handler uiHandler = new Handler();
    private String mAttachmentCachePath;        //资料缓存路径

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        mStatusBarHeight = StatusBarUtils.getStatusBarHeight(this);

        //是否使用测试环境的API
        HttpHostUtil.IS_TEST_SERVICE = false;
        //是否启用日志
        EasyLog.Companion.getDEFAULT().setEnable(true);
        initHttp();
    }

    @Override
    public void reLogin() {
        clearLoginCacheInfo();
//        AppExit();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * @return 返回资料缓存路径
     */
    public String getAttachmentCachePath() {
        if (TextUtils.isEmpty(mAttachmentCachePath))
            mAttachmentCachePath = getFilesDir() + File.separator + "attachments";

        return mAttachmentCachePath;
    }

    public Handler getUiHandler() {
        return uiHandler;
    }

    /**
     * 清空登录缓存信息
     */
    public void clearLoginCacheInfo() {
        SPUserEntity spUserEntity = EasySharedPreferences.load(SPUserEntity.class);
        spUserEntity.userInfo = null;
        spUserEntity.commit();
        SPAuthEntity spAuthEntity = EasySharedPreferences.load(SPAuthEntity.class);
        spAuthEntity.topsession = null;
        spAuthEntity.commit();
    }

    private void initHttp() {
        //设置请求头
        HttpHeaders headers = getCommonHttpHeaders();
        //设置请求参数
        EasyHttp.getInstance()
                .debug("==HTTP==>>", true)
                .addCommonHeaders(headers)//设置全局公共头
                .addInterceptor(new MyHeaderTokenInterceptor(headers));//token追加拦截器

    }

    public HttpHeaders getCommonHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", "application/json;charset=UTF-8");
        headers.put("ex-source", "1");//0-小程序；1-安卓；2-IOS
        headers.put("ex-platform", "1");//0-小程序；1-安卓；2-IOS
        return headers;
    }

    public static MyApplication getInstance() {
        return myApplication;
    }

    /**
     * 查询App是否是登录状态
     *
     * @return true 表示已经登录，否则表示未登录状态
     */
    public boolean isLogin() {
        SPAuthEntity authEntity = EasySharedPreferences.load(SPAuthEntity.class);
        return !TextUtils.isEmpty(authEntity.topsession);
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        return mStatusBarHeight;
    }
}
