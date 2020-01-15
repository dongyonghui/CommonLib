package com.dyh.commonlib.ui.view;

import com.dyh.common.lib.mvp.MVPView;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/26/026 18:30
 * 描述：上传图片
 */
public interface IUploadImageView extends MVPView {
    /**
     * 上传图片成功
     *
     * @param url
     */
    void onUploadImageSuccess(String url);
}
