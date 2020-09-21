package com.dyh.android.winehall.presenter;

import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpNoViewCallBack;
import com.dyh.common.lib.http.model.ResponseOptional;
import com.dyh.common.lib.mvp.MVPPresenter;
import com.dyh.common.lib.update_app.UpdateAppBean;
import com.dyh.common.lib.update_app.UpdateAppManager;
import com.dyh.common.lib.update_app.utils.UpdateAppHttpUtil;
import com.dyh.android.winehall.MyApplication;
import com.dyh.android.winehall.R;
import com.dyh.android.winehall.entity.local.SPUserEntity;
import com.dyh.android.winehall.http.ApiPathConstants;
import com.dyh.android.winehall.view.IAppUpdateView;

import org.jetbrains.annotations.Nullable;

/**
 * 作者：Allan
 * 时间：2019/9/17/017
 * 邮箱：648731994@qq.com
 * 描述：首页逻辑
 */
public class AppUpdatePresenter extends MVPPresenter<IAppUpdateView> {
    public AppUpdatePresenter(@Nullable IAppUpdateView view) {
        super(view);
    }

    public void checkUpdate() {
        EasyHttp.get(ApiPathConstants.APP_VERSION_PATH)
                .execute(new MyHttpNoViewCallBack<UpdateAppBean>() {
                    @Override
                    public void onSuccess(ResponseOptional<UpdateAppBean> responseBean) {
                        UpdateAppBean updateAppBean = responseBean.getIncludeNull();
                        SPUserEntity spUserEntity = EasySharedPreferences.load(SPUserEntity.class);
                        spUserEntity.isHaveNewVersion = false;
                        if (null == updateAppBean || !updateAppBean.isUpdate()) {
                            EasyToast.getDEFAULT().show(R.string.info_isNewVersion);
                            spUserEntity.commit();
                            return;
                        }
                        spUserEntity.isHaveNewVersion = true;
                        spUserEntity.commit();
                        new UpdateAppManager
                                .Builder()
                                .setIgnoreDefParams(true)
                                .setThemeColor(getActivity().getResources().getColor(R.color.text_0bb))
                                //当前Activity
                                .setActivity(getActivity())
                                .setHeaders(MyApplication.getInstance().getCommonHttpHeaders().headersMap)
                                //实现httpManager接口的对象
                                .setHttpManager(new UpdateAppHttpUtil())
                                .build()
                                .updateByResult(updateAppBean);
                    }
                }, getLifecycleProvider());

    }

}
