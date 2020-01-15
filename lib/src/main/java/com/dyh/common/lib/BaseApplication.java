package com.dyh.common.lib;

import android.app.Activity;
import android.app.Application;

import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.easy.EasyAndroid;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.cache.converter.GsonDiskConverter;
import com.dyh.common.lib.http.interceptor.HttpLoggingInterceptor;

import java.util.Stack;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * app 需要继承此Application ，并且注册到清单文件
 */
public abstract class BaseApplication extends Application {

    private static BaseApplication myApplication;
    private static Stack<Activity> activityStack;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        EasyAndroid.init(this);
        initHttp();
    }

    /**
     * 重新登录
     */
    public abstract void reLogin();


    private void initHttp() {
        EasyHttp.init(this);

        //设置请求参数
        EasyHttp.getInstance()
                .setReadTimeOut(60 * 1000)
                .setWriteTimeOut(60 * 1000)
                .setConnectTimeout(60 * 1000)
                .addConverterFactory(GsonConverterFactory.create(JsonUtils.createGson()))//Gson转换
                .setRetryCount(3)//默认网络不好自动重试3次
                .setRetryDelay(500)//每次延时500ms重试
                .setRetryIncreaseDelay(500)//每次延时叠加500ms
                .setCacheDiskConverter(new GsonDiskConverter())//默认缓存使用Gson转化
                .setCacheMaxSize(50 * 1024 * 1024)//设置缓存大小为50M
                .setCacheVersion(1)//缓存版本为1
                .setCertificates()//信任所有证书
                .addInterceptor(new HttpLoggingInterceptor("GouKu", true));//处理自己业务的拦截器

    }

    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAllActivity();
        } catch (Exception e) {
        }
    }


    public static BaseApplication getInstance() {
        return myApplication;
    }
}
