package com.dyh.android.winehall.ui.pop;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dyh.common.lib.dw.mylistener.MyTextChangeListener;
import com.dyh.common.lib.dw.util.AppTools;
import com.dyh.common.lib.easy.EasyLog;
import com.dyh.android.winehall.R;

import java.text.MessageFormat;

/**
 * 提交信息弹框
 */
public class SubmitInfoPopWindow extends PopupWindow {

    private final Activity context;

    private int alpha = 0x3f000000;
    private EditText mInputEditText;
    private TextView mHintTextView;

    private OnSubmitInfoListener mOnSubmitInfoListener;

    public OnSubmitInfoListener getOnSubmitInfoListener() {
        return mOnSubmitInfoListener;
    }

    public SubmitInfoPopWindow setOnSubmitInfoListener(OnSubmitInfoListener mOnSubmitInfoListener) {
        this.mOnSubmitInfoListener = mOnSubmitInfoListener;
        return this;
    }

    public SubmitInfoPopWindow setHintInfo(String textString) {
        mHintTextView.setText(textString);
        return this;
    }

    public SubmitInfoPopWindow(Activity context) {
        this.context = context;
        initView();
    }

    public void show(View v) {
        showAtLocation(v, Gravity.NO_GRAVITY, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        mInputEditText.setText(null);
        mInputEditText.requestFocus();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AppTools.showSoftKeyboard(mInputEditText);
            }
        }, 200);
    }

    //设置阴影层的透明度
    public SubmitInfoPopWindow setAlpha(int mAlpha) {
        alpha = mAlpha;
        return this;
    }

    private View.OnLayoutChangeListener onLayoutChangeListener = new View.OnLayoutChangeListener() {
        @Override
        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            EasyLog.Companion.getDEFAULT().e(MessageFormat.format(
                    "布局变化参数：top = {0}，bottom= {1}，oldTop = {2}，oldBottom= {3}"
                    , top, bottom, oldTop, oldBottom
            ));
            //现在认为只要控件将Activity向上推的高度超过了50，就认为软键盘弹起
//            if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > 50)) {
//
//            }
            //现在认为只要控件将Activity向下推的高度超过了50，就认为软键盘隐藏
            if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > 50)) {
                dismiss();
            }
        }
    };

    private void initView() {
        View popView = View.inflate(context, R.layout.pop_submit_info, null);
        //设置view
        this.setContentView(popView);
        getContentView().addOnLayoutChangeListener(onLayoutChangeListener);
        setOutsideTouchable(true);
        setFocusable(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popView.setClipToOutline(false);
        }
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popView.findViewById(R.id.mSubmitTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (null != mOnSubmitInfoListener) {
                    mOnSubmitInfoListener.onSubmit(mInputEditText.getText().toString());
                }
            }
        });
        //设置宽高（也可设置为LinearLayout.LayoutParams.MATCH_PARENT或者LinearLayout.LayoutParams.MATCH_PARENT）
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow的焦点
        this.setFocusable(true);

        //设置背景透明
        this.setBackgroundDrawable(new ColorDrawable(alpha));

        mHintTextView = popView.findViewById(R.id.mHintTextView);
        mInputEditText = popView.findViewById(R.id.mInputEditText);
        //设置提示信息显示隐藏
        mInputEditText.addTextChangedListener(new MyTextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (TextUtils.isEmpty(mInputEditText.getText())) {
                    mHintTextView.setVisibility(View.VISIBLE);
                } else {
                    mHintTextView.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 监听
     */
    public interface OnSubmitInfoListener {
        void onSubmit(String submitInfo);
    }

}
