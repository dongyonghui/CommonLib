package com.dyh.android.winehall.entity.response;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/17 0017
 * description:附件资料
 */
public class AttachmentItemBean {

    private String uuid;
    private String name;
    private String path_show;
    private String size;
    private String source_id;
    private String mimetype;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath_show() {
        return path_show;
    }

    public void setPath_show(String path_show) {
        this.path_show = path_show;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }
}
