package com.dyh.commonlib.presenter;

import android.text.TextUtils;

import com.dyh.commonlib.entity.local.LocalPrintDeliveryListBean;
import com.dyh.commonlib.entity.request.GetPrintDeliveryListRequest;
import com.dyh.commonlib.entity.response.PrintDeliveryListItemResultBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IPageView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpCallBack;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：GouKu_Toolkit
 * 类描述：
 * 修改备注：打印配送单
 */
public class PrintDeliveryOrderPresenter extends MVPPresenter<IPageView<LocalPrintDeliveryListBean>> {

    public PrintDeliveryOrderPresenter( IPageView<LocalPrintDeliveryListBean> view) {
        super(view);
    }

    /**
     * 刷新头牌菜分类列表
     */
    public void refreshList(GetPrintDeliveryListRequest request) {
        getView().showList(null);//先清空列表
        loadListData(request);
    }


    /**
     * 获取首页分类列表
     *
     * @param requestBean
     */
    private void loadListData(GetPrintDeliveryListRequest requestBean) {
        EasyHttp.post(ApiPathConstants.GET_DELIVER_PRINTINFO_LIST)
                .upObject(requestBean)
                .execute(new MyHttpCallBack<List<PrintDeliveryListItemResultBean>>(getView()) {
                    @Override
                    public void onSuccess(Optional<List<PrintDeliveryListItemResultBean>> listOptional) {
                        getView().showList(parseServerData2LocalData(listOptional.getIncludeNull()));
                    }
                });
    }


    /**
     * 将服务器数据转换为本地UI数据
     *
     * @param orderListResultBeans
     * @return
     */
    private List<LocalPrintDeliveryListBean> parseServerData2LocalData(List<PrintDeliveryListItemResultBean> orderListResultBeans) {
        List<LocalPrintDeliveryListBean> list = new ArrayList<>();
        if (null != orderListResultBeans) {
            //数据按照楼宇分组
            for (PrintDeliveryListItemResultBean orderListResultBean : orderListResultBeans) {
                String addressName = orderListResultBean.getDeliveryAddressName();
                if (TextUtils.isEmpty(addressName)) {
                    addressName = "--";
                }
                //检查是否有相同的楼宇存在
                boolean isHaveSameAddress = false;
                for (LocalPrintDeliveryListBean localPrintDeliveryListBean : list) {
                    if (addressName.equals(localPrintDeliveryListBean.deliveryAddressName)) {
                        isHaveSameAddress = true;
                        break;
                    }
                }
                //如果没有发现相同的楼宇则新增一个楼宇添加进去
                if (!isHaveSameAddress) {
                    LocalPrintDeliveryListBean localPrintDeliveryListBean = new LocalPrintDeliveryListBean();
                    localPrintDeliveryListBean.deliveryAddressName = orderListResultBean.getDeliveryAddressName();
                    localPrintDeliveryListBean.localPrintDeliveryAddressFloorListBeanList = new ArrayList<>();
                    list.add(localPrintDeliveryListBean);
                }
            }

            //遍历楼宇数组
            //按照楼宇分组，将不同的楼层分组信心填入楼宇信息
            for (LocalPrintDeliveryListBean localPrintDeliveryListBean : list) {
                String deliveryAddressName = localPrintDeliveryListBean.deliveryAddressName;
                //遍历所有的数据信息
                for (PrintDeliveryListItemResultBean orderListResultBean : orderListResultBeans) {
                    //筛选出相同楼宇的信息中不同的楼层信息
                    if (deliveryAddressName.equals(orderListResultBean.getDeliveryAddressName())) {
                        boolean isHaveSameFloor = false;//是否有相同的楼层
                        //遍历楼层信息
                        for (LocalPrintDeliveryListBean.LocalPrintDeliveryAddressFloorListBean floorListBean : localPrintDeliveryListBean.localPrintDeliveryAddressFloorListBeanList) {
                            String floor = floorListBean.floor;
                            if (TextUtils.isEmpty(floor)) {
                                floor = "--";
                            }
                            //添加数据到楼层信息中
                            if (floor.equals(orderListResultBean.getFloor())) {
                                isHaveSameFloor = true;
                                floorListBean.listItemResultBeanList.add(orderListResultBean);
                            }
                        }

                        //如果没有相同的楼层，则新增楼层
                        if (!isHaveSameFloor) {
                            LocalPrintDeliveryListBean.LocalPrintDeliveryAddressFloorListBean floorListBean = new LocalPrintDeliveryListBean.LocalPrintDeliveryAddressFloorListBean();
                            floorListBean.deliveryAddressName = orderListResultBean.getDeliveryAddressName();
                            floorListBean.floor = orderListResultBean.getFloor();
                            floorListBean.listItemResultBeanList = new ArrayList<>();
                            floorListBean.listItemResultBeanList.add(orderListResultBean);
                            localPrintDeliveryListBean.localPrintDeliveryAddressFloorListBeanList.add(floorListBean);
                        }
                    }
                }
            }
        }
        return list;
    }
}
