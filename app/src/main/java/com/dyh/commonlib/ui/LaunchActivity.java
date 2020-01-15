package com.dyh.commonlib.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.local.SPEntity;
import com.dyh.common.lib.dw.util.SystemSettingUtil;
import com.dyh.common.lib.easy.EasyPermissions;
import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.easy.PermissionAlwaysDenyNotifier;

import org.jetbrains.annotations.NotNull;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/**
 * 程序入口页面
 */
public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        //SDK在Android 6.0下需要进行运行检测的权限如下：
        EasyPermissions.create(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                // 设置授权结果回调
                .callback(new Function1<Boolean, Unit>() {
                    @Override
                    public Unit invoke(Boolean aBoolean) {
                        if (aBoolean) {
                            SPEntity spEntity = EasySharedPreferences.load(SPEntity.class);
                            if (spEntity.loginResponseBean != null && !TextUtils.isEmpty(spEntity.loginResponseBean.getToken())) {
                                startActivity(new Intent(LaunchActivity.this, HomeActivity.class));
                            } else {
                                startActivity(new Intent(LaunchActivity.this, LoginActivity.class));
                            }
                            finish();
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
}
