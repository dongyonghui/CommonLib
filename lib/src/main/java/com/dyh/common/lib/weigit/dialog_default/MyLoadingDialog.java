package com.dyh.common.lib.weigit.dialog_default;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dyh.common.lib.R;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/4 0004 11:07
 * description:
 */
public class MyLoadingDialog extends Dialog {
    private TextView mMessageTextView;

    public MyLoadingDialog(@NonNull Activity mActivity) {
        super(mActivity);

        Window dialogWindow = getWindow();
        if (null != dialogWindow)
            dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);// 设置背景透明

        View view = LayoutInflater.from(mActivity).inflate(R.layout.common_lib_dialog_loading, null);
        mMessageTextView = view.findViewById(R.id.mMessageTextView);
        setContentView(view);
    }

    public void setMessage(String message) {
        mMessageTextView.setText(message);
        if (TextUtils.isEmpty(message)) {
            mMessageTextView.setVisibility(View.GONE);
        }
    }


}
