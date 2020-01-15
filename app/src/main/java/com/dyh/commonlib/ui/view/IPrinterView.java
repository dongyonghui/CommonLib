package com.dyh.commonlib.ui.view;

import com.dyh.common.lib.mvp.MVPView;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/2/002 15:03
 * 描述：打印功能视图
 */
public interface IPrinterView extends MVPView {
    /**
     * 打印完成
     */
    void onPrintFinish();
}
