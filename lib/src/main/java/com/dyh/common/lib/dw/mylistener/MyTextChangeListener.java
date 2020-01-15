package com.dyh.common.lib.dw.mylistener;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/11/011 11:17
 * 描述：自定义文本变化监听器，复写所有方法单不实现功能
 */
public class MyTextChangeListener implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
