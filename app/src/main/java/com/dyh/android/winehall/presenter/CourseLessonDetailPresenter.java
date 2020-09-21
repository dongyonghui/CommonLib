package com.dyh.android.winehall.presenter;

import com.dyh.android.winehall.R;
import com.dyh.android.winehall.constants.AppHttpKey;
import com.dyh.android.winehall.entity.local.EventMessageBean;
import com.dyh.android.winehall.entity.response.CategoryItemResponseBean;
import com.dyh.android.winehall.entity.response.CourseLessonDetailResponseBean;
import com.dyh.android.winehall.entity.response.DanmuResponseBean;
import com.dyh.android.winehall.http.ApiPathConstants;
import com.dyh.android.winehall.view.ICourseLessonDetailView;
import com.dyh.common.lib.dw.util.MathUtil;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpCallBack;
import com.dyh.common.lib.http.callback.MyHttpNoViewCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.model.ResponseOptional;
import com.dyh.common.lib.mvp.MVPPresenter;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/14 0014
 * description: 课程--课节详情
 */
public class CourseLessonDetailPresenter extends MVPPresenter<ICourseLessonDetailView> {

    private long mCurrentDanmuRequestTime;  //弹幕本次请求的开始时间
    private long mNextDanmuRequestTime;     //弹幕下次请求的开始时间
    private String uuid;                    //课节ID

    public CourseLessonDetailPresenter(@Nullable ICourseLessonDetailView view) {
        super(view);
        mCurrentDanmuRequestTime = 0;
        mNextDanmuRequestTime = 0;
    }

    /**
     * 加载课程详情数据
     *
     * @param lessonId
     */
    public void loadCourseDetailData(String lessonId) {
        EasyHttp.get(ApiPathConstants.GET_LESSON + "/" + lessonId)
                .params(AppHttpKey.SUPER, "1")
                .execute(new MyHttpCallBack<CourseLessonDetailResponseBean>(getView()) {
                    @Override
                    public void onSuccess(ResponseOptional<CourseLessonDetailResponseBean> response) {
                        super.onSuccess(response);
                        getView().showDetail(response.getIncludeNull());
                    }
                }, getLifecycleProvider());
    }


    /**
     * 加载课程详情数据
     *
     * @param isCollected 是否已经收藏
     */
    public void collect(String mCourseId, final boolean isCollected) {
        EasyHttp.post((isCollected ? ApiPathConstants.COURSE_UNCOLLECT
                : ApiPathConstants.COURSE_COLLECT))
                .params(AppHttpKey.SOURCE_ID, mCourseId)
                .execute(new ProgressDialogCallBack<String>(new ProgressDialogDefault(getActivity())) {
                    @Override
                    public void onSuccess(ResponseOptional<String> response) {
                        EasyToast.getDEFAULT().show(response.getIncludeNull());
                        EventBus.getDefault().post(new EventMessageBean(EventMessageBean.CODE_COLLECT_STATUS_CHANGED));
                        getView().refreshCollectStatus(!isCollected);
                    }
                }, getLifecycleProvider());
    }

    /**
     * 获取快速定位滚动位置的Tab列表
     *
     * @return
     */
    public List<CategoryItemResponseBean> getTabList() {
        List<CategoryItemResponseBean> categoryItemResponseBeans = new ArrayList<>();
        String[] tabs = getActivity().getResources().getStringArray(R.array.courseLessonDetail_Tabs);
        for (int i = 0; i < tabs.length; i++) {
            CategoryItemResponseBean itemResponseBean = new CategoryItemResponseBean();
            itemResponseBean.category_id = String.valueOf(i);
            itemResponseBean.title = tabs[i];
            categoryItemResponseBeans.add(itemResponseBean);
        }
        return categoryItemResponseBeans;
    }

    /**
     * 加载列表数据
     *
     * @param uuid 课节ID
     */
    public void loadListData(String uuid) {
        this.uuid = uuid;
        getData();
    }

    private void getData() {
        EasyHttp.get(ApiPathConstants.GET_LESSON_BARRAGE)
                .params(AppHttpKey.SOURCE_ID, uuid)
                .params(AppHttpKey.TIME, String.valueOf(mCurrentDanmuRequestTime))
                .execute(new MyHttpNoViewCallBack<DanmuResponseBean>() {
                    @Override
                    public void onSuccess(ResponseOptional<DanmuResponseBean> response) {
                        DanmuResponseBean danmuResponseBean = response.getIncludeNull();
                        if (null == danmuResponseBean) return;
                        //展示弹幕信息
                        getView().showDanmu(danmuResponseBean.list);
                        //更新时间
                        mNextDanmuRequestTime = MathUtil.getLongNumber(danmuResponseBean.next_time);
                    }
                }, getLifecycleProvider());
    }

    /**
     * 根据视频播放的时间，更新弹幕刷新的时间
     *
     * @param currentVideoTime
     */
    public void refreshCurrentTimeByVideoTime(long currentVideoTime) {

        //下次弹幕开始时间，为0代表后续没有弹幕了
        if (this.mNextDanmuRequestTime <= 0) {
            return;
        }
        //下次弹幕开始时间小于当前的开始时间，则跳过
        if (this.mNextDanmuRequestTime <= this.mCurrentDanmuRequestTime) {
            return;
        }
        //播放时间超过下次时间时，进行请求下一页弹幕信息
        if (currentVideoTime >= this.mNextDanmuRequestTime) {
            this.mCurrentDanmuRequestTime = mNextDanmuRequestTime;
            getData();
        }
    }
}
