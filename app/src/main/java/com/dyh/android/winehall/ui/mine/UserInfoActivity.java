package com.dyh.android.winehall.ui.mine;

import android.os.Bundle;

import com.dyh.android.winehall.R;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import org.jetbrains.annotations.Nullable;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/4 0004 14:40
 * description:
 */
public class UserInfoActivity extends BaseMVPActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    public void initPage(@Nullable Bundle savedInstanceState) {

    }

    @Nullable
    @Override
    public CommonTitleBar getCommonTitleBar() {
        return null;
    }
}
