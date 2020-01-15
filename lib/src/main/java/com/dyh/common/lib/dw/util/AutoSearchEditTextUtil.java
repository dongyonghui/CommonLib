package com.dyh.common.lib.dw.util;


import android.os.Handler;

import com.dyh.common.lib.easy.EasyLog;

/**
 * 作者：DongYonghui
 * 时间：2019/9/23/023
 * 邮箱：648731994@qq.com
 * 描述：自动搜索输入框工具类
 */
public class AutoSearchEditTextUtil {

    /**
     * 确认执行监听回调
     */
    public interface OnActionDoListener {
        void onActionDo(String message);
    }

    private final Handler mHandler = new Handler();
    private String message;
    private final long DELAYED_TIME = 500;//间隔超过此时间后开始执行搜索动作
    private OnActionDoListener mOnActionDoListener;

    public AutoSearchEditTextUtil(OnActionDoListener mOnActionDoListener) {
        this.mOnActionDoListener = mOnActionDoListener;
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            EasyLog.Companion.getDEFAULT().d("开始执行搜索：" + message);
            if (mOnActionDoListener != null) {
                mOnActionDoListener.onActionDo(message);
            }
        }
    };

    public void onTextChanged(String message) {
        this.message = message;
        mHandler.removeCallbacks(mRunnable);
        mHandler.postDelayed(mRunnable, DELAYED_TIME);
    }
}
