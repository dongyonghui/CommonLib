package com.dyh.commonlib.entity.local;

import android.text.TextUtils;

import com.dyh.commonlib.entity.printer.DeliveryAddressPrinterHeaderBean;
import com.dyh.commonlib.entity.printer.DeliveryAddressPrinterItemBean;
import com.dyh.commonlib.entity.response.PrintDeliveryListItemResultBean;
import com.dyh.common.lib.dw.util.MathUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：DongYonghui
 * 日期：2019/10/30/030
 * 配送地址管理本地列表使用的数据
 * 按楼宇分组的信息
 */
public class LocalPrintDeliveryListBean {
    public boolean isChecked = false;//是否选中（本地列表UI使用，默认选中）
    public String deliveryAddressName;// 楼宇
    public List<LocalPrintDeliveryAddressFloorListBean> localPrintDeliveryAddressFloorListBeanList;//相同楼宇的楼层分组数据

    /**
     * 楼层列表信息
     */
    public static class LocalPrintDeliveryAddressFloorListBean {
        public boolean isChecked = false;//是否选中（本地列表UI使用，默认选中）
        public String deliveryAddressName;// <int>, // 楼
        public String floor;// <int>, // 楼层
        public List<PrintDeliveryListItemResultBean> listItemResultBeanList;//相同楼宇的分组数据

        public DeliveryAddressPrinterHeaderBean getPrinterHeaderBean() {
            DeliveryAddressPrinterHeaderBean headerBean = new DeliveryAddressPrinterHeaderBean();
            List<DeliveryAddressPrinterHeaderBean.PersonInfoBean> personInfoBeans = new ArrayList<>();
            List<PrintDeliveryListItemResultBean.SkuItemBean> skuItemBeans = new ArrayList<>();
            headerBean.setSkuList(skuItemBeans);
            headerBean.setPersonList(personInfoBeans);

            //设置楼层信息
            headerBean.setDeliveryAddressName(deliveryAddressName);
            headerBean.setQrcode(getQrcode());
            headerBean.setFloor(floor + "层");

            //列表项信息
            List<DeliveryAddressPrinterItemBean> printerItemBeanList = new ArrayList<>();
            headerBean.printerItemBeanList = printerItemBeanList;
            //设置header汇总信息
            //添加用户信息
            for (PrintDeliveryListItemResultBean printDeliveryListItemResultBean : listItemResultBeanList) {
                DeliveryAddressPrinterHeaderBean.PersonInfoBean personInfoBean = new DeliveryAddressPrinterHeaderBean.PersonInfoBean();
                personInfoBean.setUserName(printDeliveryListItemResultBean.getUserName());
                personInfoBean.setUserPhone(printDeliveryListItemResultBean.getUserPhone());
                personInfoBeans.add(personInfoBean);

                List<PrintDeliveryListItemResultBean.SkuItemBean> tempSkuList = printDeliveryListItemResultBean.getSkuList();
                if (null != tempSkuList) {
                    for (PrintDeliveryListItemResultBean.SkuItemBean floorSkuItemBean : tempSkuList) {
                        String skuName = floorSkuItemBean.getSkuName();
                        if (TextUtils.isEmpty(skuName)) {
                            skuName = "--";
                        }
                        boolean isHaveSameSku = false;
                        for (PrintDeliveryListItemResultBean.SkuItemBean skuItemBean : skuItemBeans) {
                            if (skuName.equals(skuItemBean.getSkuName())) {
                                isHaveSameSku = true;
                                int totalCount = MathUtil.getIntegerNumber(skuItemBean.getSkuCount()) + MathUtil.getIntegerNumber(floorSkuItemBean.getSkuCount());
                                skuItemBean.setSkuCount(String.valueOf(totalCount));
                            }
                        }

                        if (!isHaveSameSku) {
                            PrintDeliveryListItemResultBean.SkuItemBean skuItemBean = new PrintDeliveryListItemResultBean.SkuItemBean();
                            skuItemBean.setSkuCount(floorSkuItemBean.getSkuCount());
                            skuItemBean.setSkuName(floorSkuItemBean.getSkuName());
                            skuItemBeans.add(skuItemBean);
                        }
                    }
                }


                //设置列表项信息
                DeliveryAddressPrinterItemBean printerItemBean = new DeliveryAddressPrinterItemBean();
                printerItemBean.setFloorAndRemark(printDeliveryListItemResultBean.getFloor() + "层" + (TextUtils.isEmpty(printDeliveryListItemResultBean.getRemark()) ? "" : printDeliveryListItemResultBean.getRemark()));
                printerItemBean.setOrderNumber(printDeliveryListItemResultBean.getOrderNumber());
                printerItemBean.setSkuList(printDeliveryListItemResultBean.getSkuList());
                printerItemBean.setUserAddress(printDeliveryListItemResultBean.getUserAddress());
                printerItemBean.setUserName(printDeliveryListItemResultBean.getUserName());
                printerItemBean.setUserPhone(printDeliveryListItemResultBean.getUserPhone());
                printerItemBeanList.add(printerItemBean);
            }
            return headerBean;
        }

        public String getQrcode() {
            if (listItemResultBeanList == null || listItemResultBeanList.size() == 0) {
                return null;
            }
            return listItemResultBeanList.get(0).getQrcode();
        }
    }
}
