package com.dyh.commonlib.entity.local;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/19/019 17:34
 * 描述：首页按钮
 */
public class HomeActionBean {
    public String buttonName;
    public int buttonImgResId;
    public Class targetClass;

    public HomeActionBean(String buttonName, int buttonImgResId, Class targetClass) {
        this.buttonName = buttonName;
        this.buttonImgResId = buttonImgResId;
        this.targetClass = targetClass;
    }
}
