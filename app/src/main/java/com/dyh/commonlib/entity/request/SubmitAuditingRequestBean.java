package com.dyh.commonlib.entity.request;

import com.dyh.commonlib.entity.BaseHttpRequestBean;
import com.dyh.commonlib.entity.response.CategoryItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/26/026 10:44
 * 描述：操作商品状态
 */
public class SubmitAuditingRequestBean extends BaseHttpRequestBean {
    public String auditingType;
    public String spu;
    public String shopId;
    public String name;//商品名称
    public String addPrice;
    public String isAgree;
    public String reason;
    public String sort;//权重
    public List<String> shopWareTagsIds;//标签列表
    private List<CateListBean> cateList;//优选时间列表
    private List<CategoryItemBean> categoryList;//分类列表

    public List<CateListBean> getCateList() {
        return cateList;
    }

    public void setCateList(List<CateListBean> cateList) {
        this.cateList = cateList;
    }

    public List<CategoryItemBean> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryItemBean> categoryList) {
        this.categoryList = new ArrayList<>();
        if (null != categoryList) {
            for (CategoryItemBean categoryItemBean : categoryList) {
                CategoryItemBean itemBean = new CategoryItemBean();
                itemBean.setName(categoryItemBean.getName());
                itemBean.setId(categoryItemBean.getId());
                itemBean.setActivityId(categoryItemBean.getActivityId());
                this.categoryList.add(itemBean);
            }
        }
    }

    public static class CateListBean {
        /**
         * id : 58
         * time : 00:00-23:45
         */
        private String id;
        private String time;
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
