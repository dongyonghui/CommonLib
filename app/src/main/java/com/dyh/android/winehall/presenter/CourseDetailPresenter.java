package com.dyh.android.winehall.presenter;

import android.content.res.Resources;

import com.dyh.android.winehall.R;
import com.dyh.android.winehall.constants.AppHttpKey;
import com.dyh.android.winehall.constants.UIConfig;
import com.dyh.android.winehall.entity.common.CourseDetailChildBean;
import com.dyh.android.winehall.entity.common.LessonsBean;
import com.dyh.android.winehall.entity.local.LocalCourseCommentTotalInfoBean;
import com.dyh.android.winehall.entity.local.LocalCourseDetailBottomInfoBean;
import com.dyh.android.winehall.entity.response.CategoryItemResponseBean;
import com.dyh.android.winehall.entity.response.CourseCommentItemResponseBean;
import com.dyh.android.winehall.entity.response.CourseDetailResponseBean;
import com.dyh.android.winehall.http.ApiPathConstants;
import com.dyh.android.winehall.view.ICourseDetailView;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpCallBack;
import com.dyh.common.lib.http.callback.MyHttpNoViewCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.exception.ApiException;
import com.dyh.common.lib.http.model.ResponseOptional;
import com.dyh.common.lib.mvp.MVPPresenter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/7 0007 10:46
 * description:课程详情
 */
public class CourseDetailPresenter extends MVPPresenter<ICourseDetailView> {

    private String mCourseId;                                   //分类数据
    private CourseDetailResponseBean mCourseDetailResponseBean; //课程详情信息
    private List<CourseCommentItemResponseBean> mCommentList;   //评价列表
    private int tabIndex = 0;                                   //列表根据快速定位Tab索引分组
    private final int TAB_INDEX_EXPAND_ALL_LESSONS = -100;      //展开全部课程Tab索引
    public static final int TAB_INDEX_NONE = -1;                //未找到Tab索引

    //Tab索引对应的列表位置映射表
    private final HashMap<Integer, Integer> mTabIndex2PositionMap = new HashMap<>();

    public CourseDetailPresenter(@Nullable ICourseDetailView view) {
        super(view);
    }

    /**
     * 加载课程详情数据
     *
     * @param courseId
     */
    public void loadCourseDetailData(String courseId) {
        this.mCourseId = courseId;
        EasyHttp.get(ApiPathConstants.GET_COURSE + "/" + courseId)
                .execute(new MyHttpCallBack<CourseDetailResponseBean>(getView()) {
                    @Override
                    public void onSuccess(ResponseOptional<CourseDetailResponseBean> response) {
                        super.onSuccess(response);
                        mCourseDetailResponseBean = response.getIncludeNull();
                        getView().showDetail2Ui(mCourseDetailResponseBean);

                        //获取评价列表
                        getCommentListData();
                    }
                }, getLifecycleProvider());
    }

    /**
     * 加载课程详情数据
     *
     * @param isCollected 是否已经收藏
     */
    public void collect(final boolean isCollected) {
        EasyHttp.post((isCollected ? ApiPathConstants.COURSE_UNCOLLECT
                : ApiPathConstants.COURSE_COLLECT))
                .params(AppHttpKey.SOURCE_ID, mCourseId)
                .execute(new ProgressDialogCallBack<String>(new ProgressDialogDefault(getActivity())) {
                    @Override
                    public void onSuccess(ResponseOptional<String> response) {
                        EasyToast.getDEFAULT().show(response.getIncludeNull());
                        getView().refreshCollectStatus(!isCollected);
                    }
                }, getLifecycleProvider());
    }

    /**
     * 根据tab索引获取底部列表对应的列表位置索引
     *
     * @param tabIndex
     * @return TAB_INDEX_NONE表示没有找到
     */
    public int getBottomListIndexByTabIndex(int tabIndex) {
        Integer index = mTabIndex2PositionMap.get(tabIndex);
        return null == index ? TAB_INDEX_NONE : index;
    }

    /**
     * 获取底部列表中【展开全部课程】列表项对应的列表位置索引
     *
     * @return TAB_INDEX_NONE表示没有找到
     */
    public int getExpandAllLessonsItemIndex() {
        Integer index = mTabIndex2PositionMap.get(TAB_INDEX_EXPAND_ALL_LESSONS);
        return null == index ? TAB_INDEX_NONE : index;
    }

    /**
     * 获取没有展示到列表的其他课程列表信息
     */
    public List<LocalCourseDetailBottomInfoBean> getNoVisibleLessonList() {
        List<LocalCourseDetailBottomInfoBean> bottomInfoBeanList = new ArrayList<>();
        if (null == mCourseDetailResponseBean) return bottomInfoBeanList;

        List<LessonsBean> lessonsBeanList = mCourseDetailResponseBean.getLessons();
        if (null == lessonsBeanList) return bottomInfoBeanList;

        int lessonCount = lessonsBeanList.size();

        //未超出指定数量时不需要展开更多
        if (lessonCount <= UIConfig.COURSE_DETAIL_NEED_EXPAND_COUNT) return bottomInfoBeanList;

        for (int i = UIConfig.COURSE_DETAIL_NEED_EXPAND_COUNT; i < lessonCount; i++) {
            LessonsBean lessonsBean = lessonsBeanList.get(i);
            lessonsBean.isLastOne = (i == (lessonsBeanList.size() - 1));
            bottomInfoBeanList.add(getBottomCourseBean(lessonsBean));
        }

        return bottomInfoBeanList;
    }

    /**
     * 刷新 列表分组对应快速定位Tab index
     *
     * @param dataList
     */
    public void refreshBottomListGroupIndex2TabIndex(List<LocalCourseDetailBottomInfoBean> dataList) {
        if (null == dataList) return;

        tabIndex = 0;
        for (int i = 0; i < dataList.size(); i++) {
            LocalCourseDetailBottomInfoBean detailBottomInfoBean = dataList.get(i);
            if (LocalCourseDetailBottomInfoBean.TYPE_TILE_COMMENT == detailBottomInfoBean.type
                    || LocalCourseDetailBottomInfoBean.TYPE_TILE_DEFAULT == detailBottomInfoBean.type) {
                mTabIndex2PositionMap.put(tabIndex++, i);
            }
        }
    }


    /**
     * 展示课程详情底部信息
     */
    private void showCourseDetailBottomInfo() {
        //先清空缓存
        mTabIndex2PositionMap.clear();
        tabIndex = 0;

        if (null == mCourseDetailResponseBean) return;

        List<LocalCourseDetailBottomInfoBean> bottomInfoBeanList = new ArrayList<>();
        Resources resources = getActivity().getResources();

        //添加课程列表标题
        mTabIndex2PositionMap.put(tabIndex, bottomInfoBeanList.size());
        bottomInfoBeanList.add(getBottomDefaultTitleBean(resources.getString(R.string.courseDetail_bottomTitle_course),
                "(" + mCourseDetailResponseBean.getLesson_count() + ")"));
        //添加课程列表
        List<LessonsBean> lessonsBeanList = mCourseDetailResponseBean.getLessons();
        if (null != lessonsBeanList) {
            int lessonCount = lessonsBeanList.size();
            int count = Math.min(UIConfig.COURSE_DETAIL_NEED_EXPAND_COUNT, lessonCount);
            for (int i = 0; i < count; i++) {
                LessonsBean lessonsBean = lessonsBeanList.get(i);
                lessonsBean.isLastOne = (i == (count - 1));
                bottomInfoBeanList.add(getBottomCourseBean(lessonsBean));
            }
            //添加展开课程项，超出指定数量时才需要展开更多
            if (lessonCount > UIConfig.COURSE_DETAIL_NEED_EXPAND_COUNT) {
                mTabIndex2PositionMap.put(TAB_INDEX_EXPAND_ALL_LESSONS, bottomInfoBeanList.size());
                bottomInfoBeanList.add(new LocalCourseDetailBottomInfoBean(LocalCourseDetailBottomInfoBean.TYPE_SHOW_ALL_LESSONS, tabIndex));
            }
        }

        tabIndex++;

        //添加课程介绍标题
        mTabIndex2PositionMap.put(tabIndex, bottomInfoBeanList.size());
        bottomInfoBeanList.add(getBottomDefaultTitleBean(resources.getString(R.string.courseDetail_bottomTitle_detail),
                null));
        //添加课程介绍
        bottomInfoBeanList.addAll(getCourseDetailInfoListBean());

        tabIndex++;

        //添加评价标题
        mTabIndex2PositionMap.put(tabIndex, bottomInfoBeanList.size());
        bottomInfoBeanList.add(getCommentTitleBean());
        //添加评价列表
        if (null != mCommentList)
            for (CourseCommentItemResponseBean courseCommentItemResponseBean : mCommentList) {
                LocalCourseDetailBottomInfoBean bottomInfoBean = new LocalCourseDetailBottomInfoBean(LocalCourseDetailBottomInfoBean.TYPE_ITEM_COMMENT, tabIndex);
                bottomInfoBean.data = courseCommentItemResponseBean;
                bottomInfoBeanList.add(bottomInfoBean);
            }
        //添加评价Footer
        LocalCourseDetailBottomInfoBean localCourseDetailBottomInfoBean = new LocalCourseDetailBottomInfoBean(LocalCourseDetailBottomInfoBean.TYPE_TILE_COMMENT_FOOTER, tabIndex);
        localCourseDetailBottomInfoBean.data = MessageFormat.format(resources.getString(R.string.format_courseDetail_commentFooter)
                , mCourseDetailResponseBean.getComment_count());
        bottomInfoBeanList.add(localCourseDetailBottomInfoBean);

        tabIndex++;

        //添加推荐标题
        mTabIndex2PositionMap.put(tabIndex, bottomInfoBeanList.size());
        bottomInfoBeanList.add(getBottomDefaultTitleBean(resources.getString(R.string.courseDetail_bottomTitle_recommend),
                null));
        getView().showDetailBottomListInfo2Ui(bottomInfoBeanList);

        tabIndex++;
    }


    @NotNull
    private LocalCourseDetailBottomInfoBean getCommentTitleBean() {
        LocalCourseCommentTotalInfoBean localCourseCommentTotalInfoBean = new LocalCourseCommentTotalInfoBean();
        if (null != mCourseDetailResponseBean) {
            localCourseCommentTotalInfoBean.countInfo = "(" + mCourseDetailResponseBean.getComment_count() + ")";
            localCourseCommentTotalInfoBean.score = mCourseDetailResponseBean.getScore();
        } else {
            localCourseCommentTotalInfoBean.countInfo = "(0)";
            localCourseCommentTotalInfoBean.score = "5";
        }
        LocalCourseDetailBottomInfoBean localCourseDetailBottomInfoBean
                = new LocalCourseDetailBottomInfoBean(LocalCourseDetailBottomInfoBean.TYPE_TILE_COMMENT, tabIndex);
        localCourseDetailBottomInfoBean.data = localCourseCommentTotalInfoBean;
        return localCourseDetailBottomInfoBean;
    }

    @NotNull
    private List<LocalCourseDetailBottomInfoBean> getCourseDetailInfoListBean() {
        List<LocalCourseDetailBottomInfoBean> list = new ArrayList<>();
        if (null == mCourseDetailResponseBean) return list;
        if (null == mCourseDetailResponseBean.getDetail()) return list;

        List<CourseDetailChildBean> child = mCourseDetailResponseBean.getDetail().getChild();
        if (null == child) return list;
        for (CourseDetailChildBean courseDetailChildBean : child) {
            list.addAll(getTextByChildBean(-1, courseDetailChildBean));
        }
        return list;
    }

    private List<LocalCourseDetailBottomInfoBean> getTextByChildBean(int type, CourseDetailChildBean currentChild) {
        List<LocalCourseDetailBottomInfoBean> list = new ArrayList<>();
        if (null == currentChild) return list;

        if ("h1".equalsIgnoreCase(currentChild.getTag())) {
            type = LocalCourseDetailBottomInfoBean.TYPE_ITEM_COURSE_DETAIL_H1;
        } else if ("h2".equalsIgnoreCase(currentChild.getTag())) {
            type = LocalCourseDetailBottomInfoBean.TYPE_ITEM_COURSE_DETAIL_H2;
        } else if ("p".equalsIgnoreCase(currentChild.getTag())) {
            type = LocalCourseDetailBottomInfoBean.TYPE_ITEM_COURSE_DETAIL_TEXT;
        } else if ("hr".equalsIgnoreCase(currentChild.getTag())) {
            LocalCourseDetailBottomInfoBean bottomInfoBean =
                    new LocalCourseDetailBottomInfoBean(LocalCourseDetailBottomInfoBean.TYPE_ITEM_COURSE_DETAIL_HR, tabIndex);
            list.add(bottomInfoBean);
        } else if ("img".equalsIgnoreCase(currentChild.getTag())) {
            LocalCourseDetailBottomInfoBean bottomInfoBean = new LocalCourseDetailBottomInfoBean(
                    LocalCourseDetailBottomInfoBean.TYPE_ITEM_COURSE_DETAIL_IMAGE, tabIndex);
            bottomInfoBean.data = null != currentChild.attr
                    ? currentChild.attr.src
                    : "";
            list.add(bottomInfoBean);
        } else if ("text".equalsIgnoreCase(currentChild.getNode())) {
            LocalCourseDetailBottomInfoBean bottomInfoBean =
                    new LocalCourseDetailBottomInfoBean(type, tabIndex);
            bottomInfoBean.data = currentChild.getText();
            list.add(bottomInfoBean);
        } else {
            type = LocalCourseDetailBottomInfoBean.TYPE_ITEM_COURSE_DETAIL_TEXT;
        }

        //遍历子列表项添加到列表
        if (null != currentChild.getChild()) {
            List<CourseDetailChildBean> childBeanList = currentChild.getChild();
            for (CourseDetailChildBean courseDetailChildBean : childBeanList) {
                list.addAll(getTextByChildBean(type, courseDetailChildBean));
            }
        }

        return list;
    }

    /**
     * 获取底部列表课程项数据
     *
     * @param lessonsBean
     * @return
     */
    private LocalCourseDetailBottomInfoBean getBottomCourseBean(@NonNull LessonsBean lessonsBean) {
        LocalCourseDetailBottomInfoBean bottomInfoBean
                = new LocalCourseDetailBottomInfoBean(LocalCourseDetailBottomInfoBean.TYPE_ITEM_LESSON, tabIndex);
        bottomInfoBean.data = lessonsBean;
        return bottomInfoBean;
    }

    /**
     * 获取底部列表标题
     *
     * @param title
     * @param countInfo
     * @return
     */
    private LocalCourseDetailBottomInfoBean getBottomDefaultTitleBean(String title, String countInfo) {
        LocalCourseDetailBottomInfoBean.TitleDefaultData titleDefaultData =
                new LocalCourseDetailBottomInfoBean.TitleDefaultData();
        titleDefaultData.title = title;
        titleDefaultData.countInfo = countInfo;
        LocalCourseDetailBottomInfoBean bottomInfoBean = new LocalCourseDetailBottomInfoBean(LocalCourseDetailBottomInfoBean.TYPE_TILE_DEFAULT, tabIndex);
        bottomInfoBean.data = titleDefaultData;
        return bottomInfoBean;
    }

    /**
     * 获取快速定位滚动位置的Tab列表
     *
     * @return
     */
    public List<CategoryItemResponseBean> getQuickScrollTabList() {
        List<CategoryItemResponseBean> categoryItemResponseBeans = new ArrayList<>();
        String[] tabs = getActivity().getResources().getStringArray(R.array.courseDetail_quickScrollTabs);
        for (int i = 0; i < tabs.length; i++) {
            CategoryItemResponseBean itemResponseBean = new CategoryItemResponseBean();
            itemResponseBean.category_id = String.valueOf(i);
            itemResponseBean.title = tabs[i];
            categoryItemResponseBeans.add(itemResponseBean);
        }
        return categoryItemResponseBeans;
    }


    /**
     * 获取评价列表
     */
    private void getCommentListData() {
        EasyHttp.get(ApiPathConstants.GET_COURSE_COMMENTS + "/" + mCourseId)
                .params(AppHttpKey.PAGE, String.valueOf(UIConfig.PAGE_NO_START_DEFAULT))
                .params(AppHttpKey.PAGE_SIZE, String.valueOf(UIConfig.COURSE_DETAIL_COMMENT_PAGE_SIZE))
                .execute(new MyHttpNoViewCallBack<List<CourseCommentItemResponseBean>>() {
                    @Override
                    public void onSuccess(ResponseOptional<List<CourseCommentItemResponseBean>> response) {
                        mCommentList = response.getIncludeNull();
                        showCourseDetailBottomInfo();
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        showCourseDetailBottomInfo();
                    }
                }, getLifecycleProvider());
    }
}
