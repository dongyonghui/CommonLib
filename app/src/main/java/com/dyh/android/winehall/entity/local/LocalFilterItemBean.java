package com.dyh.android.winehall.entity.local;

import java.io.Serializable;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/4/30 0030 16:03
 * description:
 */
public class LocalFilterItemBean implements Serializable {
    public String id;//网络请求时的参数 value值
    public String name;//UI展示的标签名称
    public boolean isSelected = false;

    public LocalFilterItemBean() {
    }

    public LocalFilterItemBean(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
