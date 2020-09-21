package com.dyh.android.winehall.entity.common;

import java.util.List;

public class LecturerBean {
    private String lecturer_id;
    private String name;
    private String avatar_show;
    private String verify_status;
    private String brief;
    private List<String> verify_show;

    public String getLecturer_id() {
        return lecturer_id;
    }

    public void setLecturer_id(String lecturer_id) {
        this.lecturer_id = lecturer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar_show() {
        return avatar_show;
    }

    public void setAvatar_show(String avatar_show) {
        this.avatar_show = avatar_show;
    }

    public String getVerify_status() {
        return verify_status;
    }

    public void setVerify_status(String verify_status) {
        this.verify_status = verify_status;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public List<String> getVerify_show() {
        return verify_show;
    }

    public void setVerify_show(List<String> verify_show) {
        this.verify_show = verify_show;
    }
}