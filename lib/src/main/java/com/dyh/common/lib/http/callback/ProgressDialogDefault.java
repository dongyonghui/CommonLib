package com.dyh.common.lib.http.callback;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.dyh.common.lib.R;
import com.dyh.common.lib.dw.util.DensityUtils;
import com.dyh.common.lib.http.subsciber.IProgressDialog;

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
        Dialog dialog = new Dialog(mActivity);
        dialog.setContentView(R.layout.progress_dialog_default_loading_view);
        TextView textView = dialog.findViewById(R.id.status_hint_content);
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
