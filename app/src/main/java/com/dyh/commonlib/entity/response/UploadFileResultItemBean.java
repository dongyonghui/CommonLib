package com.dyh.commonlib.entity.response;

import com.dyh.commonlib.entity.BaseHttpResponseBean;

/**
 * 作者：DongYonghui
 * 日期：2019/7/18/018
 * 上传文件结果
 */
public class UploadFileResultItemBean extends BaseHttpResponseBean {
    private String key;
    private String originalName;
    private String contentType;
    private int size;
    private String url;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
