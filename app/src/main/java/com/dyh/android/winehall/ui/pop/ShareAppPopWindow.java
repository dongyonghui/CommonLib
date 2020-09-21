package com.dyh.android.winehall.ui.pop;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.dyh.common.lib.dw.util.AppTools;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.android.winehall.R;

/**
 * 分享app弹框
 */
public class ShareAppPopWindow extends PopupWindow {

    private final Activity context;

    private int alpha = 0x3f000000;

    private String downloadUrl;

    public ShareAppPopWindow(Activity context, String downloadUrl) {
        this.downloadUrl = downloadUrl;
        this.context = context;
        initView();
    }

    public void show(View v) {
        showAtLocation(v, Gravity.NO_GRAVITY, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
    }

    //设置阴影层的透明度
    public ShareAppPopWindow setAlpha(int mAlpha) {
        alpha = mAlpha;
        return this;
    }

    private void initView() {
        View popView = View.inflate(context, R.layout.pop_share_app, null);
        //设置view
        this.setContentView(popView);
        popView.findViewById(R.id.mShareTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSuccess = AppTools.copy(context, downloadUrl);
                if (isSuccess) {
                    EasyToast.getDEFAULT().show(R.string.info_copySuccess);
                }
                dismiss();
            }
        });
        popView.findViewById(R.id.mCancelTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        //设置宽高（也可设置为LinearLayout.LayoutParams.MATCH_PARENT或者LinearLayout.LayoutParams.MATCH_PARENT）
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        //设置PopupWindow的焦点
        this.setFocusable(true);

        //设置背景透明
        this.setBackgroundDrawable(new ColorDrawable(alpha));
    }
}
