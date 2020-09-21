package com.dyh.android.winehall.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.dyh.common.lib.dw.util.SystemSettingUtil;
import com.dyh.common.lib.easy.EasyPermissions;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.easy.PermissionAlwaysDenyNotifier;
import com.dyh.common.lib.weigit.titlebar.statusbar.StatusBarUtils;
import com.dyh.android.winehall.R;

import org.jetbrains.annotations.NotNull;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/**
 * 程序入口页面
 */
public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        StatusBarUtils.transparentStatusBar(getWindow());

        //SDK在Android 6.0下需要进行运行检测的权限如下，如不是全局必须，可以在使用的时候申请
        EasyPermissions.create(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                // 设置授权结果回调
                .callback(new Function1<Boolean, Unit>() {
                    @Override
                    public Unit invoke(Boolean aBoolean) {
                        if (aBoolean) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    goIntoApp();
                                }
                            }, 1000);
                        } else {
                            EasyToast.getDEFAULT().show("请允许必要的权限");
                            finish();
                        }
                        return null;
                    }
                })
                // 当权限被默认拒绝时。调起弹窗提醒需要用户去主动开启权限
                .alwaysDenyNotifier(new PermissionAlwaysDenyNotifier() {
                    @Override
                    public void onAlwaysDeny(@NotNull String[] permissions, @NotNull Activity activity) {
                        EasyToast.getDEFAULT().show("权限已默认拒绝，请打开相关权限");
                        SystemSettingUtil.goToSetting(LaunchActivity.this);
                    }
                })
                .request(this);
    }

    private void goIntoApp() {
       /* SPEntity spEntity = EasySharedPreferences.load(SPEntity.class);
        if (!spEntity.isHaveAlreadyShowWelcomeInfo) {
            startActivity(new Intent(LaunchActivity.this, WelcomeActivity.class));
            spEntity.isHaveAlreadyShowWelcomeInfo = true;
            spEntity.commit();
        } else if (spEntity.loginResponseBean != null && !TextUtils.isEmpty(spEntity.loginResponseBean.getAccess_token())) {
            startActivity(new Intent(LaunchActivity.this, HomeActivity.class));
        } else {
            startActivity(new Intent(LaunchActivity.this, LoginActivity.class));
        }*/
        startActivity(new Intent(LaunchActivity.this, HomeActivity.class));
        finish();
    }
}
