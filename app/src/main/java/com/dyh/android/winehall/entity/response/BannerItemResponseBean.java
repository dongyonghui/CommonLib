package com.dyh.android.winehall.entity.response;

import com.dyh.android.winehall.entity.BaseHttpResponseBean;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/5 0005 20:18
 * description:banner列表项
 */
public class BannerItemResponseBean extends BaseHttpResponseBean {
    private String type;
    private String source_id;
    private String title;
    private String image_show;
    private String order;
    private String link_type;
    private String link_params;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_show() {
        return image_show;
    }

    public void setImage_show(String image_show) {
        this.image_show = image_show;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getLink_type() {
        return link_type;
    }

    public void setLink_type(String link_type) {
        this.link_type = link_type;
    }

    public String getLink_params() {
        return link_params;
    }

    public void setLink_params(String link_params) {
        this.link_params = link_params;
    }
}
