package com.dyh.android.winehall.entity.response;

import com.dyh.android.winehall.entity.BaseHttpResponseBean;
import com.dyh.common.lib.dw.util.MathUtil;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/4 0004 11:18
 * description: 用户信息数据
 */
public class UserInfoResponseBean extends BaseHttpResponseBean {
    private String user_id;
    private String avatar_show;
    private String username;
    private String signature;
    private String mobile_show;
    private String nickname;
    private String unionid;
    private String last_login_time;
    private String last_login_ip;
    private String valid_start;
    private String valid_end;
    private String status;
    private String vip;

    /**
     * @return true表示是vip，否则不是
     */
    public boolean isVip() {
        return MathUtil.getIntegerNumber(vip) > 0;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAvatar_show() {
        return avatar_show;
    }

    public void setAvatar_show(String avatar_show) {
        this.avatar_show = avatar_show;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getMobile_show() {
        return mobile_show;
    }

    public void setMobile_show(String mobile_show) {
        this.mobile_show = mobile_show;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getLast_login_ip() {
        return last_login_ip;
    }

    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip;
    }

    public String getValid_start() {
        return valid_start;
    }

    public void setValid_start(String valid_start) {
        this.valid_start = valid_start;
    }

    public String getValid_end() {
        return valid_end;
    }

    public void setValid_end(String valid_end) {
        this.valid_end = valid_end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }
}
