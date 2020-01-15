package com.dyh.commonlib.model;

import com.dyh.common.lib.weigit.pop_filter.bean.FiltrateBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器数据管理
 */
public class FilterModel {
    /**
     * @return 返回头牌菜筛选条件数据
     */
    public static List<FiltrateBean> getTpcGoodsFilterData() {
        List<FiltrateBean> dictList = new ArrayList<>();
        FiltrateBean filtrateBean;
        List<FiltrateBean.Children> childrenList;

        filtrateBean = new FiltrateBean();
        dictList.add(filtrateBean);
        filtrateBean.setKey("name");
        filtrateBean.setTitleName("请输入商品名称或店铺名称");
        filtrateBean.setType(FiltrateBean.TYPE_INPUT);

        filtrateBean = new FiltrateBean();
        dictList.add(filtrateBean);
        childrenList = new ArrayList<>();
        filtrateBean.setChildren(childrenList);
        filtrateBean.setTitleName("菜品类型");
        filtrateBean.setKey("auditingType");
        childrenList.add(new FiltrateBean.Children("全部", "0"));
        childrenList.add(new FiltrateBean.Children("头牌菜", "2"));
        childrenList.add(new FiltrateBean.Children("优选菜", "4"));
        childrenList.add(new FiltrateBean.Children("首单特惠", "5"));
        childrenList.add(new FiltrateBean.Children("限时秒杀", "7"));


        filtrateBean = new FiltrateBean();
        dictList.add(filtrateBean);
        childrenList = new ArrayList<>();
        filtrateBean.setChildren(childrenList);
        filtrateBean.setTitleName("审核状态");
        filtrateBean.setKey("auditingStatus");
        childrenList.add(new FiltrateBean.Children("全部", "0"));
        childrenList.add(new FiltrateBean.Children("待审核", "1", true));
        childrenList.add(new FiltrateBean.Children("审核通过", "2"));
        childrenList.add(new FiltrateBean.Children("被驳回", "3"));

        return dictList;
    }

    /**
     * @return 返回配送地址管理筛选条件数据
     */
    public static List<FiltrateBean> getDeliveryFilterData() {
        List<FiltrateBean> dictList = new ArrayList<>();
        FiltrateBean filtrateBean;
        filtrateBean = new FiltrateBean();
        dictList.add(filtrateBean);
        filtrateBean.setKey("address");
        filtrateBean.setTitleName("请输入配送地址名称");
        filtrateBean.setType(FiltrateBean.TYPE_INPUT);
        return dictList;
    }
}
