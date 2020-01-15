package com.dyh.commonlib.presenter;

import android.text.TextUtils;

import com.dyh.commonlib.entity.response.TimeItemBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IDefaultOptionView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;


/**
 * 新建或编辑优选菜展示时间
 */
public class AddOrEditSeckillTimePresenter extends MVPPresenter<IDefaultOptionView> {


    public AddOrEditSeckillTimePresenter( IDefaultOptionView view) {
        super(view);
    }

    /**
     * 提交请求
     *
     * @param requestBean
     */
    public void addOrEdit(TimeItemBean requestBean) {
        String url = ApiPathConstants.ADD_SECKILL_TIME;
        if (!TextUtils.isEmpty(requestBean.getId())) {
            url = ApiPathConstants.EDIT_SECKILL_TIME;
        }
        EasyHttp.post(url)
                .upObject(requestBean)
                .execute(new ProgressDialogCallBack<Object>(new ProgressDialogDefault(getActivity())) {
                    @Override
                    public void onSuccess(Optional<Object> listOptional) {
                        getView().onOptionOk();
                    }
                });
    }
}
