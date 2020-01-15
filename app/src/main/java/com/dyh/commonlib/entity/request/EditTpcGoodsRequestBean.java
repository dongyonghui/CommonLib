package com.dyh.commonlib.entity.request;

import com.dyh.commonlib.entity.BaseHttpRequestBean;
import com.dyh.commonlib.entity.response.TpcGoodsItemBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/27/027 10:30
 * 描述：编辑头牌菜商品
 */
public class EditTpcGoodsRequestBean extends BaseHttpRequestBean {

    /**
     * auditingType : 4
     * shopId : 111403
     * spu : 17957
     * name : 红薯粉
     * desc : 1111
     * pictures : https://gouku-ware.oss-cn-zhangjiakou.aliyuncs.com/image_1561016421954.jpg
     * limitNum : 200
     * personLimitNum : 0
     * pictureList : ["https://gouku-ware.oss-cn-zhangjiakou.aliyuncs.com/image_1561016421954.jpg"]
     * wareType : 3
     * standards : [{"sku":1795702,"name":"正常","pictures":null,"xprice":0,"onlineStorePrice":"0.02","oldXprice":0,"oldOnlineStorePrice":0.02,"storePrice":0,"storeGroupPrice":null,"onlineStoreGroupPrice":"0.01","addPrice":"0.01","pictureList":["https://image.goukugogo.com/20191127104331378c6c210e3"],"onlineStorePriceUnit":0.02,"onlineStoreGroupPriceUnit":0.01,"sumPrice":0.01}]
     */

    private String auditingType;
    private String shopId;
    private String spu;
    private String name;
    private String desc;
    private String pictures;
    private String limitNum;
    private String personLimitNum;
    private String wareType;
    private List<String> pictureList;
    private List<TpcGoodsItemBean.StandardsBean> standards;

    public String getAuditingType() {
        return auditingType;
    }

    public void setAuditingType(String auditingType) {
        this.auditingType = auditingType;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getSpu() {
        return spu;
    }

    public void setSpu(String spu) {
        this.spu = spu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(String limitNum) {
        this.limitNum = limitNum;
    }

    public String getPersonLimitNum() {
        return personLimitNum;
    }

    public void setPersonLimitNum(String personLimitNum) {
        this.personLimitNum = personLimitNum;
    }

    public String getWareType() {
        return wareType;
    }

    public void setWareType(String wareType) {
        this.wareType = wareType;
    }

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    public List<TpcGoodsItemBean.StandardsBean> getStandards() {
        return standards;
    }

    public void setStandards(List<TpcGoodsItemBean.StandardsBean> standards) {
        this.standards = standards;
    }
}
