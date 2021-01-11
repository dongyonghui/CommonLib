package com.dyh.common.lib.http.callback;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.dyh.common.lib.R;
import com.dyh.common.lib.dw.util.DensityUtils;
import com.dyh.common.lib.http.subsciber.IProgressDialog;
import com.dyh.common.lib.weigit.dialog_default.MyLoadingDialog;

/**
 * 作者：Allan
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
        MyLoadingDialog dialog = new MyLoadingDialog(mActivity);
        TextView textView = dialog.findViewById(R.id.mMessageTextView);
        textView.setText(mMessage);
        int dialogSize = DensityUtils.dp2px(mActivity, 160);
        Window window = dialog.getWindow();
        if (null != window) {
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = dialogSize;
            params.height = dialogSize;
            params.dimAmount = 0.2f;
            window.setAttributes(params);

            window.setBackgroundDrawableResource(R.color.transparent);
        }

        return dialog;
    }
}
