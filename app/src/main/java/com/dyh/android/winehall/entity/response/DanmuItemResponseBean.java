package com.dyh.android.winehall.entity.response;

import com.dyh.android.winehall.entity.BaseHttpResponseBean;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/17 0017
 * description: 弹幕列表项
 */
public class DanmuItemResponseBean extends BaseHttpResponseBean {

    private String time;
    private String content;
    private String created_at;
    private UserInfoResponseBean user;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public UserInfoResponseBean getUser() {
        return user;
    }

    public void setUser(UserInfoResponseBean user) {
        this.user = user;
    }

}
