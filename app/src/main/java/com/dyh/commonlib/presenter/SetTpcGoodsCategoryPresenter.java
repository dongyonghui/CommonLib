package com.dyh.commonlib.presenter;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.request.GetCategoryRequestBean;
import com.dyh.commonlib.entity.response.CategoryItemBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IPageView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpCallBack;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;
import com.dyh.common.lib.recyclerview_helper.entity.SectionEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置分类
 */
public class SetTpcGoodsCategoryPresenter extends MVPPresenter<IPageView<SectionEntity<CategoryItemBean>>> {


    public SetTpcGoodsCategoryPresenter( IPageView<SectionEntity<CategoryItemBean>> view) {
        super(view);
    }

    /**
     * 刷新头牌菜分类列表
     */
    public void refreshTpcCategoryList() {
        getView().showList(null);//先清空列表
        GetCategoryRequestBean getCategoryRequestBean = new GetCategoryRequestBean();
        getCategoryRequestBean.wareType = "1";
        loadHomeCategoryListData(getCategoryRequestBean);
    }


    /**
     * 获取首页分类列表
     *
     * @param requestBean
     */
    private void loadHomeCategoryListData(GetCategoryRequestBean requestBean) {
        EasyHttp.post(ApiPathConstants.GET_HOME_CATEGORY)
                .upObject(requestBean)
                .execute(new MyHttpCallBack<List<CategoryItemBean>>(getView()) {
                    @Override
                    public void onSuccess(Optional<List<CategoryItemBean>> listOptional) {
                        appendCategoryList(getActivity().getString(R.string.selectHomeCategory), listOptional);
                        loadDetailCategoryListData();
                    }
                });
    }


    /**
     * 加载详情分类列表
     */
    private void loadDetailCategoryListData() {
        EasyHttp.post(ApiPathConstants.GET_DETAIL_CATEGORY)
                .upObject(new GetCategoryRequestBean())
                .execute(new MyHttpCallBack<List<CategoryItemBean>>(getView()) {
                    @Override
                    public void onSuccess(Optional<List<CategoryItemBean>> listOptional) {
                        appendCategoryList(getActivity().getString(R.string.selectDetailCategory), listOptional);
                    }
                });
    }

    /**
     * 将获取到分类列表处理完数据后添加到UI上
     *
     * @param headerTitle  分组标题
     * @param listOptional 分组项
     */
    private void appendCategoryList(String headerTitle, Optional<List<CategoryItemBean>> listOptional) {
        List<CategoryItemBean> list = listOptional.getIncludeNull();
        if (null == list) {
            return;
        }
        List<SectionEntity<CategoryItemBean>> sectionEntityList = new ArrayList<>();
        //添加分组头部
        SectionEntity<CategoryItemBean> sectionEntity = new SectionEntity<CategoryItemBean>(true, headerTitle);
        sectionEntityList.add(sectionEntity);
        //添加列表项
        for (CategoryItemBean categoryItemBean : list) {
            sectionEntity = new SectionEntity<>(categoryItemBean);
            sectionEntityList.add(sectionEntity);
        }
        getView().appendList(sectionEntityList);
    }
}
