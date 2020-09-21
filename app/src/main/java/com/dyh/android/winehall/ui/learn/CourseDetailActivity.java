package com.dyh.android.winehall.ui.learn;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dyh.android.winehall.MyApplication;
import com.dyh.android.winehall.R;
import com.dyh.android.winehall.constants.IntentConstants;
import com.dyh.android.winehall.constants.ServerConstants;
import com.dyh.android.winehall.constants.UIConfig;
import com.dyh.android.winehall.entity.common.LecturerBean;
import com.dyh.android.winehall.entity.common.LessonsBean;
import com.dyh.android.winehall.entity.local.EventMessageBean;
import com.dyh.android.winehall.entity.local.LocalCourseDetailBottomInfoBean;
import com.dyh.android.winehall.entity.local.SPUserEntity;
import com.dyh.android.winehall.entity.response.CourseDetailResponseBean;
import com.dyh.android.winehall.entity.response.HomeLearnCourseItemResponseBean;
import com.dyh.android.winehall.entity.response.UserInfoResponseBean;
import com.dyh.android.winehall.presenter.CourseDetailPresenter;
import com.dyh.android.winehall.ui.adapter.CategoryTabListAdapter;
import com.dyh.android.winehall.ui.adapter.CourseDetailBannerImageAdapter;
import com.dyh.android.winehall.ui.adapter.CourseDetailBottomInfoListAdapter;
import com.dyh.android.winehall.ui.adapter.CourseDetailFlagListAdapter;
import com.dyh.android.winehall.view.ICourseDetailView;
import com.dyh.android.winehall.weigit.AppBarStateChangeListener;
import com.dyh.common.lib.dw.util.DensityUtils;
import com.dyh.common.lib.dw.util.MathUtil;
import com.dyh.common.lib.dw.util.MyStringUtil;
import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.glide.EasyGlide;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.banner.Banner;
import com.dyh.common.lib.weigit.banner.indicator.RectangleIndicator;
import com.dyh.common.lib.weigit.titlebar.statusbar.StatusBarUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.Nullable;

import java.text.MessageFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 课程详情
 */
public class CourseDetailActivity extends BaseMVPActivity implements ICourseDetailView {

    @BindView(R.id.mAppBarLayout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.mToolbar)
    Toolbar mToolbar;
    @BindView(R.id.mBanner)
    Banner mBanner;
    @BindView(R.id.mRecyclerTab)
    RecyclerView mRecyclerTab;

    @BindView(R.id.mCollapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.mBottomTempView)
    View mBottomTempView;
    @BindView(R.id.mHeaderImageView)
    ImageView mHeaderImageView;
    @BindView(R.id.mTeacherNameTextView)
    TextView mTeacherNameTextView;
    @BindView(R.id.mTeacherRemarkTextView)
    TextView mTeacherRemarkTextView;
    @BindView(R.id.mTeacherRemark2TextView)
    TextView mTeacherRemark2TextView;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.mFlagRecyclerView)
    RecyclerView mFlagRecyclerView;
    @BindView(R.id.mTitleTextView)
    TextView mTitleTextView;
    @BindView(R.id.mRemarkTextView)
    TextView mRemarkTextView;
    @BindView(R.id.mCourseLanguageTextView)
    TextView mCourseLanguageTextView;
    @BindView(R.id.mCourseDurationTextView)
    TextView mCourseDurationTextView;
    @BindView(R.id.mCourseTeacherCountTextView)
    TextView mCourseTeacherCountTextView;

    @BindView(R.id.mStatusInfoTextView)
    TextView mStatusInfoTextView;
    @BindView(R.id.mStatusSubInfoTextView)
    TextView mStatusSubInfoTextView;
    @BindView(R.id.mSignUpTextView)
    TextView mSignUpTextView;

    @BindView(R.id.mFreeGroup)
    Group mFreeGroup;
    @BindView(R.id.mPriceGroup)
    Group mPriceGroup;
    @BindView(R.id.mPriceTotalTextView)
    TextView mPriceTotalTextView;
    @BindView(R.id.mPriceVerifyTextView)
    TextView mPriceVerifyTextView;

    @BindView(R.id.mGetVipTextView)
    TextView mGetVipTextView;


    //标题栏按钮
    private MenuItem mMenuBack;
    private MenuItem mMenuShare;
    private MenuItem mMenuCollection;

    //分类tab适配器
    private final CategoryTabListAdapter mCategoryTabListAdapter =
            new CategoryTabListAdapter(R.layout.item_home_category);
    //底部详情信息列表适配器
    private CourseDetailBottomInfoListAdapter mCourseDetailBottomInfoListAdapter;

    //处理逻辑
    private final CourseDetailPresenter mCourseDetailPresenter
            = new CourseDetailPresenter(this);

    private boolean isStatusBarTrans;               //是否是透明标题栏（低版本手机不支持）
    private String mDetailId;                       //详情id
    private LecturerBean mLecturerBean;             //讲师信息
    private String mTitleString;                    //标题
    private boolean isNeedBuy;                      //是否需要购买
    private CourseDetailResponseBean mCourseDetailResponseBean;//课程详情数据
    private boolean isCollected;                    //是否已收藏
    private boolean isToolbarExpand;                //Toolbar是否是展开状态

    /**
     * 构建传递的参数
     *
     * @param courseItemResponseBean
     * @return
     */
    public static Bundle buildIntentData(HomeLearnCourseItemResponseBean courseItemResponseBean) {
        Bundle bundle = new Bundle();
        if (null != courseItemResponseBean) {
            bundle.putString(IntentConstants.KEY_DETAIL_ID_STRING, courseItemResponseBean.getCourse_id());
        }
        return bundle;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_course_detail;
    }

    @Override
    public int getMultipleStatusContentViewId() {
        return R.id.mLoadingContentView;
    }

    @Override
    public void onRetryClick() {
        super.onRetryClick();

        mCourseDetailPresenter.loadCourseDetailData(mDetailId);
    }

    @OnClick(R.id.mBack2TopTextView)
    public void back2Top() {
        mAppBarLayout.setExpanded(true);
        mRecyclerView.smoothScrollToPosition(0);
    }

    @OnClick(R.id.mTeacherPanelCardView)
    public void showTeacherDetailInfo() {
        readyGo(CourseTeacherDetailActivity.class, CourseTeacherDetailActivity.buildIntentData(mLecturerBean));
    }

    @Override
    public void initPage(@Nullable Bundle savedInstanceState) {
        isStatusBarTrans = StatusBarUtils.transparentStatusBar(getWindow());

        mDetailId = getIntent().getStringExtra(IntentConstants.KEY_DETAIL_ID_STRING);

        setStatusBarMode(true);
        initTopAreaScrollAction();
        initTabList();
        initBottomInfoList();

        //设置课程原价删除线
        mStatusSubInfoTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        //加载课程详情信息
        mCourseDetailPresenter.loadCourseDetailData(mDetailId);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessageBean event) {
        //登录成功后自动刷新
        if (EventMessageBean.CODE_COLLECT_STATUS_CHANGED == event.code) {
            //刷新详情数据
            refreshCollectStatus(!isCollected);
        }
    }


    /**
     * 初始底部详情列表
     */
    private void initBottomInfoList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mCourseDetailBottomInfoListAdapter
                = new CourseDetailBottomInfoListAdapter(this);
        mRecyclerView.setAdapter(mCourseDetailBottomInfoListAdapter);

        mCourseDetailBottomInfoListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LocalCourseDetailBottomInfoBean detailBottomInfoBean = mCourseDetailBottomInfoListAdapter.getItem(position);
                if (null == detailBottomInfoBean) return;
                switch (detailBottomInfoBean.type) {
                    case LocalCourseDetailBottomInfoBean.TYPE_TILE_COMMENT_FOOTER:
                        showAllComments();
                        break;
                    case LocalCourseDetailBottomInfoBean.TYPE_SHOW_ALL_LESSONS:
                        showAllLessons();
                        break;
                    case LocalCourseDetailBottomInfoBean.TYPE_ITEM_LESSON:
                        showLessonDetail(mCourseDetailBottomInfoListAdapter.getItem(position));
                        break;
                }
            }
        });

        //列表滚动的时候，自动选中快速定位Tab 栏
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //停止滚动的时候选中tab
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    LocalCourseDetailBottomInfoBean bottomInfoBean =
                            mCourseDetailBottomInfoListAdapter.getItem(linearLayoutManager.findFirstVisibleItemPosition());
                    if (null == bottomInfoBean) return;

                    //选中快速定位tab
                    mCategoryTabListAdapter.setSelectedIndex(bottomInfoBean.titleInQuickTabIndex);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    /**
     * 展示所有评论列表
     */
    private void showAllComments() {
        readyGo(CourseCommentListActivity.class, CourseCommentListActivity.buildIntentData(mCourseDetailResponseBean));
    }

    /**
     * 展示课节详情
     *
     * @param item
     */
    private void showLessonDetail(LocalCourseDetailBottomInfoBean item) {
        if (null == item || !(item.data instanceof LessonsBean)) return;

//        Bundle bundle = CourseLessonDetailActivity.buildIntentData((LessonsBean) item.data, isNeedBuy, isCollected);
//        readyGo(CourseLessonDetailActivity.class, bundle);
    }

    /**
     * 展示全部课节信息
     */
    private void showAllLessons() {
        int showAllIndex = mCourseDetailPresenter.getExpandAllLessonsItemIndex();
        if (CourseDetailPresenter.TAB_INDEX_NONE == showAllIndex || null == mCourseDetailBottomInfoListAdapter)
            return;

        mCourseDetailBottomInfoListAdapter.remove(showAllIndex);

        //展示最后条分割线
        LocalCourseDetailBottomInfoBean detailBottomInfoBean = mCourseDetailBottomInfoListAdapter.getItem(showAllIndex - 1);
        if (null != detailBottomInfoBean && detailBottomInfoBean.data instanceof LessonsBean) {
            ((LessonsBean) detailBottomInfoBean.data).isLastOne = false;
            mCourseDetailBottomInfoListAdapter.notifyDataSetChanged();
        }

        mCourseDetailBottomInfoListAdapter.addData(showAllIndex, mCourseDetailPresenter.getNoVisibleLessonList());

        mCourseDetailPresenter.refreshBottomListGroupIndex2TabIndex(mCourseDetailBottomInfoListAdapter.getData());
    }

    /**
     * 初始化顶部区域滑动动作
     */
    private void initTopAreaScrollAction() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mMenuShare = mToolbar.getMenu().findItem(R.id.mMenuShare);
        mMenuCollection = mToolbar.getMenu().findItem(R.id.mMenuCollection);

        //设置收藏/取消收藏监听
        mMenuCollection.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                mCourseDetailPresenter.collect(isCollected);
                return false;
            }
        });
        //如果支持状态栏透明，则标题栏顶部增加状态栏间隔高度，防止内容到状态栏下面
        if (isStatusBarTrans) {
            int statusBarHeight = MyApplication.getInstance().getStatusBarHeight();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mToolbar.getLayoutParams();
            layoutParams.topMargin = statusBarHeight;
            mToolbar.setLayoutParams(layoutParams);
        }
        isToolbarExpand = true;
        //监听顶部区域展开和折叠状态变化，处理标题栏切换样式变化
        mAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, AppBarStateChangeListener.State state) {
                if (state == State.EXPANDED) {
                    //展开状态
                    isToolbarExpand = true;
                    mMenuShare.setIcon(R.mipmap.icon_title_share_expand);
                    mToolbar.setNavigationIcon(R.mipmap.icon_title_back_white);
                    mToolbar.setTitle(null);
                    setStatusBarMode(true);
                    refreshCollectStatus(isCollected);
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    isToolbarExpand = false;
                    mMenuShare.setIcon(R.mipmap.icon_title_share_black);
                    mToolbar.setNavigationIcon(R.mipmap.icon_title_back_black);
                    mToolbar.setTitle(mTitleString);
                    setStatusBarMode(false);
                    refreshCollectStatus(isCollected);
                } else {
                    //中间状态

                }
            }
        });
    }

    /**
     * 设置状态栏颜色
     *
     * @param isLightMode true表示白色字体，否则表示黑色字体
     */
    private void setStatusBarMode(boolean isLightMode) {
        //设置状态栏字体颜色
        if (isLightMode)
            StatusBarUtils.setLightMode(getWindow());
        else
            StatusBarUtils.setDarkMode(getWindow());
    }


    /**
     * 初始化tab列表
     */
    private void initTabList() {
        LinearLayoutManager tabLayout = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,
                false);
        mRecyclerTab.setLayoutManager(tabLayout);
        mCategoryTabListAdapter.setNewData(mCourseDetailPresenter.getQuickScrollTabList());
        mRecyclerTab.setAdapter(mCategoryTabListAdapter);

        //Tab切换选中项事件处理
        mCategoryTabListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mCategoryTabListAdapter.setSelectedIndex(position);
                int index = mCourseDetailPresenter.getBottomListIndexByTabIndex(position);
                if (CourseDetailPresenter.TAB_INDEX_NONE != index) {
                    LinearLayoutManager manager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                    if (null != manager)
                        manager.scrollToPositionWithOffset(index, 0);
                }
            }
        });
    }

    @Override
    public void showDetail2Ui(CourseDetailResponseBean courseDetailResponseBean) {
        this.mCourseDetailResponseBean = courseDetailResponseBean;
        if (null == courseDetailResponseBean) return;
        mTitleString = courseDetailResponseBean.getTitle();

        //是否收藏
        isCollected = courseDetailResponseBean.isIs_collected();
        refreshCollectStatus(isCollected);

        //设置用户相关信息
        SPUserEntity userEntity = EasySharedPreferences.load(SPUserEntity.class);
        UserInfoResponseBean userInfoResponseBean = userEntity.userInfo;
        boolean isVip = null != userInfoResponseBean && userInfoResponseBean.isVip();
        if (isVip) {
            //VIP隐藏开通vip组件
            mGetVipTextView.setVisibility(View.GONE);
        } else {
            //非VIP展示开通vip组件
            mGetVipTextView.setVisibility(View.VISIBLE);
        }


        if (ServerConstants.TRUE_DEFAULT_NUMBER.equals(courseDetailResponseBean.getIs_free())
                || MathUtil.getDoubleNumber(courseDetailResponseBean.getTotal_fee()) <= 0) {//如果是免费课程，或者总价 ≤ 0，不需要购买
            isNeedBuy = false;
        } else if (isVip &&
                (MathUtil.getDoubleNumber(courseDetailResponseBean.getVip_fee()) > 0)) {//是Vip 并且 vip价格 ＞ 0 ，需要购买
            isNeedBuy = true;
        } else if (!isVip &&
                (MathUtil.getDoubleNumber(courseDetailResponseBean.getTotal_fee()) > 0)) {//如果不是vip ，并且课程总价 ＞ 0，需要购买
            isNeedBuy = true;
        } else {
            isNeedBuy = false;
        }

        if (isNeedBuy) {
            //展示价格组件
            mFreeGroup.setVisibility(View.GONE);
            mPriceGroup.setVisibility(View.VISIBLE);

            //设置价格信息
            mPriceTotalTextView.setText(isVip ? courseDetailResponseBean.getVip_fee() : courseDetailResponseBean.getTotal_fee());
            mPriceVerifyTextView.setText(isVip ?
                    MessageFormat.format(getString(R.string.format_courseDetail_lineFee)
                            , courseDetailResponseBean.getTotal_fee())
                    : MessageFormat.format(getString(R.string.format_courseDetail_verifyFee)
                    , courseDetailResponseBean.getVerify_fee()));
        } else {
            //展示会员免费组件
            mFreeGroup.setVisibility(View.VISIBLE);
            mPriceGroup.setVisibility(View.GONE);

            //设置免费信息
            mStatusInfoTextView.setText(isVip ? R.string.memberFree : R.string.freeCourse);
            mStatusSubInfoTextView.setText(MessageFormat.format(getString(R.string.format_courseDetail_lineFee)
                    , courseDetailResponseBean.getLine_fee()));
        }

        //设置中间Panel信息
        mTitleTextView.setText(courseDetailResponseBean.getTitle());
        mRemarkTextView.setText(courseDetailResponseBean.getBrief());
        mCourseLanguageTextView.setText(courseDetailResponseBean.getLang());
        mCourseDurationTextView.setText(courseDetailResponseBean.getDuration());
        mCourseTeacherCountTextView.setText(courseDetailResponseBean.getLesson_count());

        //根据屏幕宽度和图片数量来动态计算指示器的宽度
        int count = 0;
        List<String> tempList = courseDetailResponseBean.getCover_show();
        if (null != tempList && tempList.size() > 0) {
            count = tempList.size();
        }

        int indicatorWidth = DensityUtils.dip2px(this, 50);//指示器默认宽度50
        if (count > 3) {
            int indicatorSpace = DensityUtils.dip2px(this, 5);//指示器默认宽度50
            int width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
            int maxWidth = (width - 40 - ((count - 1) * indicatorSpace)) / count;
            indicatorWidth = Math.min(maxWidth, indicatorWidth);
        }
        mBanner.setAdapter(new CourseDetailBannerImageAdapter(courseDetailResponseBean.getCover_show()))
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new RectangleIndicator(this))//设置指示器
                .setIndicatorWidth(indicatorWidth, indicatorWidth);


        //设置讲师信息
        mLecturerBean = courseDetailResponseBean.getLecturer();
        if (null != mLecturerBean) {
            EasyGlide.loadCircleImage(this, mLecturerBean.getAvatar_show(), mHeaderImageView, R.mipmap.person_head_default);
            mTeacherNameTextView.setText(mLecturerBean.getName());
            List<String> verifyShowArray = mLecturerBean.getVerify_show();
            String remark = UIConfig.EMPTY_DATA_DEFAULT;
            if (null != verifyShowArray && verifyShowArray.size() > 0) {
                remark = MyStringUtil.join(" / ", verifyShowArray);
            }
            mTeacherRemarkTextView.setText(remark);
            mTeacherRemark2TextView.setText(mLecturerBean.getBrief());
        }

        //添加快速定位Tab列表
        mFlagRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        CourseDetailFlagListAdapter flagListAdapter = new CourseDetailFlagListAdapter(R.layout.item_course_detail_flag_list);
        flagListAdapter.setNewData(courseDetailResponseBean.getGenres());
        mFlagRecyclerView.setAdapter(flagListAdapter);
    }

    @Override
    public void showDetailBottomListInfo2Ui(List<LocalCourseDetailBottomInfoBean> bottomInfoBeanList) {
        if (null == mCourseDetailBottomInfoListAdapter)
            initBottomInfoList();

        mCourseDetailBottomInfoListAdapter.setNewData(bottomInfoBeanList);
    }

    @Override
    public void refreshCollectStatus(boolean isCollected) {
        this.isCollected = isCollected;
        //展开+收藏
        if (isCollected && isToolbarExpand) {
            mMenuCollection.setIcon(R.mipmap.icon_title_collection_expand_red);
            return;
        }

        //折叠+收藏
        if (isCollected) {
            mMenuCollection.setIcon(R.mipmap.icon_title_collection_red);
            return;
        }

        //展开+未收藏
        if (isToolbarExpand) {
            mMenuCollection.setIcon(R.mipmap.icon_title_collection_expand);
            return;
        }

        //折叠+未收藏
        mMenuCollection.setIcon(R.mipmap.icon_title_collection_black);
    }
}
