package com.dyh.common.lib;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import com.dyh.common.lib.download.FileDownLoadService;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.easy.EasyAndroid;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.cache.converter.GsonDiskConverter;
import com.dyh.common.lib.http.interceptor.HttpLoggingInterceptor;
import com.dyh.common.lib.weigit.refresh_layout.SmartRefreshLayout;
import com.dyh.common.lib.weigit.refresh_layout.api.DefaultRefreshFooterCreator;
import com.dyh.common.lib.weigit.refresh_layout.api.RefreshFooter;
import com.dyh.common.lib.weigit.refresh_layout.api.RefreshLayout;
import com.dyh.common.lib.weigit.refresh_layout.constant.SpinnerStyle;
import com.dyh.common.lib.weigit.refresh_layout.footer.ClassicsFooter;
import com.dyh.common.lib.weigit.refresh_layout.header.ClassicsHeader;

import java.util.Stack;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * app 需要继承此Application ，并且注册到清单文件
 */
public abstract class BaseApplication extends Application {

    static {
        //启用矢量图兼容
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        //设置全局默认配置（优先级最低，会被其他设置覆盖）
        SmartRefreshLayout.setDefaultRefreshInitializer((context, layout) -> {
            //全局设置（优先级最低）
            layout.setEnableAutoLoadMore(true);
            layout.setEnableOverScrollDrag(false);
            layout.setEnableOverScrollBounce(true);
            layout.setEnableLoadMoreWhenContentNotFull(true);
            layout.setEnableScrollContentWhenRefreshed(true);
            layout.setPrimaryColorsId(R.color.general_refreshHeaderBgColor, R.color.general_refreshHeaderTextColor);
            layout.getLayout().setTag("close egg");
        });
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            //全局设置主题颜色（优先级第二低，可以覆盖 DefaultRefreshInitializer 的配置，与下面的ClassicsHeader绑定）
            ClassicsHeader header = new ClassicsHeader(context);
            header.setSpinnerStyle(SpinnerStyle.FixedBehind)
                    .setEnableLastTime(false)
                    .setProgressResource(R.drawable.ic_progress_puzzle);
            return header;
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                ClassicsFooter footer = new ClassicsFooter(context);
                footer.setSpinnerStyle(SpinnerStyle.Translate);
                return footer;
            }
        });
    }


    private static BaseApplication myApplication;
    private static Stack<Activity> activityStack;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        EasyAndroid.init(this);
        initHttp();

        mContext = this;
        mainHandler = new Handler();
    }

    /**
     * 开启下载服务
     */
    public void startDownloadService() {
        Intent intent = new Intent(this, FileDownLoadService.class);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            startForegroundService(intent);//解决android8.0以上无法启动服务的问题
        } else {
            startService(intent);
        }
    }

    /**
     * 停止下载服务
     */
    public void stopDownloadService() {
        Intent intent = new Intent(this, FileDownLoadService.class);
        stopService(intent);
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
                .addInterceptor(new HttpLoggingInterceptor("EasyHttp", true));//处理自己业务的拦截器

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

    private static Context mContext = null;
    private static Handler mainHandler;

    public static Handler getMainHandler() {
        return mainHandler;
    }

    public static Context getContext() {
        return mContext;
    }
}
