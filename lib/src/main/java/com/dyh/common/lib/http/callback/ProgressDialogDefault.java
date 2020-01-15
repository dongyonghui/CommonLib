package com.dyh.common.lib.http.callback;

import android.app.Activity;
import android.app.Dialog;

import com.dyh.common.lib.http.subsciber.IProgressDialog;

/**
 * 作者：DongYonghui
 * 时间：2019/9/17/017
 * 邮箱：648731994@qq.com
 * 描述：默认加载对话框
 */
public class ProgressDialogDefault implements IProgressDialog {

    private Activity mActivity;
    private String mMessage;

    public ProgressDialogDefault(Activity activity) {
        this(activity, "加载中...");
    }

    public ProgressDialogDefault(Activity activity, String meg) {
        this.mActivity = activity;
        this.mMessage = meg;
    }

    @Override
    public Dialog getDialog() {
        android.app.ProgressDialog dialog = new android.app.ProgressDialog(mActivity);
        dialog.setMessage(mMessage);
        return dialog;
    }
}
