package com.dyh.android.winehall.view;

import com.dyh.android.winehall.entity.response.UserInfoResponseBean;
import com.dyh.common.lib.mvp.MVPView;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020年9月4日 10点55分
 * description: 我的模块
 */
public interface IHomeMineView extends MVPView {
    void showUserInfo2View(UserInfoResponseBean userInfo);
}
