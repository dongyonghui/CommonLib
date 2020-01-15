package com.dyh.commonlib.presenter;

import com.dyh.commonlib.MyApplication;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IAppUpdateView;
import com.dyh.common.lib.mvp.MVPPresenter;
import com.dyh.common.lib.update_app.UpdateAppManager;
import com.dyh.common.lib.update_app.utils.UpdateAppHttpUtil;


/**
 * 作者：DongYonghui
 * 时间：2019/9/17/017
 * 邮箱：648731994@qq.com
 * 描述：首页逻辑
 */
public class AppUpdatePresenter extends MVPPresenter<IAppUpdateView> {
    public AppUpdatePresenter( IAppUpdateView view) {
        super(view);
    }

    public void checkUpdate() {
        new UpdateAppManager
                .Builder()
                .setIgnoreDefParams(true)
                //当前Activity
                .setActivity(getActivity())
                .setHeaders(MyApplication.getInstance().getCommonHttpHeaders().headersMap)
                //更新地址
                .setUpdateUrl(ApiPathConstants.APP_VERSION_PATH)
                //实现httpManager接口的对象
                .setHttpManager(new UpdateAppHttpUtil())
                .build()
                .update();
    }

}
