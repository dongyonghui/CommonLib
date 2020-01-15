package com.dyh.commonlib.presenter;

import android.content.res.Resources;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.BaseHttpPageRequestBean;
import com.dyh.commonlib.entity.request.DeleteGroupPrizeRequestBean;
import com.dyh.commonlib.entity.response.GroupPrizeItemBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IDefaultManageView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;
import com.dyh.common.lib.weigit.dialog_default.MyAppDialog;
import com.dyh.common.lib.weigit.dialog_default.interfaces.OnDialogClickListener;

import java.util.List;

/**
 * 团体奖励管理
 */
public class GroupPrizeManagePresenter extends MVPPresenter<IDefaultManageView<GroupPrizeItemBean>> {

    private final BaseHttpPageRequestBean requestBean = new BaseHttpPageRequestBean();


    public GroupPrizeManagePresenter( IDefaultManageView<GroupPrizeItemBean> view) {
        super(view);
    }

    public void refreshList() {
        //刷新前先清空list
        getView().showList(null);
        requestBean.page = 0;
        loadListData(requestBean);
    }

    public void loadList() {
        requestBean.page++;
        loadListData(requestBean);
    }

    /**
     * 删除
     *
     * @param itemBean
     */
    public void deleteNotice(GroupPrizeItemBean itemBean) {
        if (itemBean == null) {
            return;
        }
        Resources resources = getActivity().getResources();
        new MyAppDialog(getActivity())
                .setTitle(getActivity().getString(R.string.delete))
                .setMessage(getActivity().getString(R.string.formatString_deleteGroupPrice, itemBean.getWareTextInfo(getActivity())))
                .setNegative(resources.getString(R.string.cancel))
                .setPositive(resources.getString(R.string.delete), new OnDialogClickListener() {
                    @Override
                    public void onDialogButtonClicked(MyAppDialog dialog) {
                        DeleteGroupPrizeRequestBean requestBean = new DeleteGroupPrizeRequestBean();
                        requestBean.userTeamAwardId = itemBean.getUserTeamAwardId();
                        EasyHttp.post(ApiPathConstants.DELETE_GROUP_PRIZE)
                                .upObject(requestBean)
                                .execute(new ProgressDialogCallBack<Object>(new ProgressDialogDefault(getActivity())) {
                                    @Override
                                    public void onSuccess(Optional<Object> listOptional) {
                                        getView().onOptionSuccess();
                                        dialog.dismiss();
                                    }
                                });
                    }
                })
                .isCancelable(true)
                .show();
    }


    private void loadListData(BaseHttpPageRequestBean requestBean) {
        EasyHttp.post(ApiPathConstants.GET_GROUP_PRIZE_LIST)
                .upObject(requestBean)
                .execute(new MyHttpCallBack<List<GroupPrizeItemBean>>(getView()) {
                    @Override
                    public void onSuccess(Optional<List<GroupPrizeItemBean>> listOptional) {
                        if (requestBean.page == 0) {
                            getView().showList(listOptional.getIncludeNull());
                        } else {
                            getView().appendList(listOptional.getIncludeNull());
                        }
                    }
                });
    }
}
