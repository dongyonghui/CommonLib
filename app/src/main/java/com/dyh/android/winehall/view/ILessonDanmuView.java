package com.dyh.android.winehall.view;

import com.dyh.android.winehall.entity.response.DanmuItemResponseBean;
import com.dyh.common.lib.mvp.MVPView;

import java.util.List;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/16 0016
 * description:课节详情--弹幕
 */
public interface ILessonDanmuView extends MVPView {
    /**
     * 发送弹幕成功
     *
     * @param danmuItemResponseBean
     */
    void onSendDanmuSuccess(DanmuItemResponseBean danmuItemResponseBean);

    /**
     * 展示弹幕信息
     *
     * @param danmuItemResponseBeanList
     */
    void showDanmu(List<DanmuItemResponseBean> danmuItemResponseBeanList);

}
