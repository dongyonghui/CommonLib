package com.dyh.android.winehall.view;

import com.dyh.common.lib.mvp.MVPView;

import java.io.File;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/17 0017
 * description: 课节详情--资料
 */
public interface ILessonAttachmentView extends MVPView {
    /**
     * 展示PDF附件资料
     *
     * @param pdfFile
     */
    void showPdfAttachment(File pdfFile);

    /**
     * 加载文件失败
     */
    void onLoadFileError();
}
