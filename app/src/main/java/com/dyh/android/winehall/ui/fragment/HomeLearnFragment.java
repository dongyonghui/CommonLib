package com.dyh.android.winehall.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.dyh.android.winehall.MyApplication;
import com.dyh.android.winehall.R;
import com.dyh.android.winehall.constants.IntentConstants;
import com.dyh.android.winehall.entity.response.BannerItemResponseBean;
import com.dyh.android.winehall.entity.response.CategoryItemResponseBean;
import com.dyh.android.winehall.presenter.HomeLearnPresenter;
import com.dyh.android.winehall.ui.adapter.CategoryTabListAdapter;
import com.dyh.android.winehall.ui.adapter.HomeBannerImageAdapter;
import com.dyh.android.winehall.ui.adapter.HomeLearnViewPagerAdapter;
import com.dyh.android.winehall.view.IHomeLearnView;
import com.dyh.android.winehall.weigit.AppBarStateChangeListener;
import com.dyh.common.lib.easy.EasyLog;
import com.dyh.common.lib.mvp.MVPPresenter;
import com.dyh.common.lib.mvp.impl.BaseMVPFragment;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.banner.Banner;
import com.dyh.common.lib.weigit.banner.indicator.RectangleIndicator;
import com.dyh.common.lib.weigit.banner.util.LogUtils;
import com.dyh.common.lib.weigit.titlebar.statusbar.StatusBarUtils;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 功能：首页学酒页面
 */
public class HomeLearnFragment extends BaseMVPFragment implements IHomeLearnView {

    @BindView(R.id.mAppBarLayout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.mToolbar)
    Toolbar mToolbar;
    @BindView(R.id.mBanner)
    Banner mBanner;
    @BindView(R.id.mRecyclerTab)
    RecyclerView mRecyclerTab;
    @BindView(R.id.mTopSearchRootView)
    View mTopSearchRootView;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;

    //标题栏按钮
    private MenuItem mMenuHomeLearnSearch;
    private MenuItem mMenuHomeLearnMenu;

    //逻辑处理器
    private HomeLearnPresenter mHomeLearnPresenter = new HomeLearnPresenter(this);

    //分类tab适配器
    private CategoryTabListAdapter mCategoryTabListAdapter =
            new CategoryTabListAdapter(R.layout.item_home_category);

    private boolean isStatusBarTrans;               //是否是透明标题栏（低版本手机不支持）
    private boolean isLightMode;                    //是否是亮色的主题


    /**
     * 构建传递的参数
     *
     * @param isStatusBarTrans
     * @return
     */
    public static HomeLearnFragment buildIntentData(boolean isStatusBarTrans) {
        HomeLearnFragment homeLearnFragment = new HomeLearnFragment();

        Bundle bundle = new Bundle();
        bundle.putBoolean(IntentConstants.IS_STATUS_BAR_TRANS, isStatusBarTrans);

        homeLearnFragment.setArguments(bundle);
        return homeLearnFragment;
    }

    @Nullable
    @Override
    public MVPPresenter<?>[] createPresenters() {
        return new MVPPresenter[]{mHomeLearnPresenter};
    }

    @Override
    public int getMultipleStatusContentViewId() {
        return R.id.mLoadingContentView;
    }

    @Override
    public void onRetryClick() {
        super.onRetryClick();
        mHomeLearnPresenter.refreshAllPageDatas2View();
        showLoadingView(null);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_learn;
    }

    @Override
    public void initPage(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (null != bundle) {
            isStatusBarTrans = bundle.getBoolean(IntentConstants.IS_STATUS_BAR_TRANS, false);
        }

        isLightMode = true;
        initTabList();
        initTopAreaScrollAction();


        mHomeLearnPresenter.refreshAllPageDatas2View();
    }

    @Override
    protected void lazyLoad(boolean isFirst) {
        super.lazyLoad(isFirst);
        refreshStatusBarMode();
    }

    /**
     * 初始化顶部区域滑动动作
     */
    private void initTopAreaScrollAction() {
        mMenuHomeLearnSearch = mToolbar.getMenu().findItem(R.id.mMenuHomeLearnSearch);
        mMenuHomeLearnMenu = mToolbar.getMenu().findItem(R.id.mMenuHomeLearnMenu);
        //如果支持状态栏透明，则标题栏顶部增加状态栏间隔高度，防止内容到状态栏下面
        if (isStatusBarTrans) {
            int statusBarHeight = MyApplication.getInstance().getStatusBarHeight();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mTopSearchRootView.getLayoutParams();
            layoutParams.topMargin = statusBarHeight;
            mTopSearchRootView.setLayoutParams(layoutParams);
            layoutParams = (FrameLayout.LayoutParams) mToolbar.getLayoutParams();
            layoutParams.topMargin = statusBarHeight;
            mToolbar.setLayoutParams(layoutParams);
        }
        //监听顶部区域展开和折叠状态变化，处理标题栏切换样式变化
        mAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());
                if (state == State.EXPANDED) {
                    //展开状态
                    mMenuHomeLearnSearch.setVisible(false);
                    mMenuHomeLearnSearch.setIcon(R.mipmap.icon_search_white);
                    mMenuHomeLearnMenu.setIcon(R.mipmap.icon_menu_white);
                    mToolbar.setLogo(R.mipmap.icon_home_logo_white);
                    isLightMode = true;
                    refreshStatusBarMode();
                    mTopSearchRootView.setVisibility(View.VISIBLE);
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    mMenuHomeLearnSearch.setVisible(true);
                    mMenuHomeLearnSearch.setIcon(R.mipmap.icon_search_black);
                    mMenuHomeLearnMenu.setIcon(R.mipmap.icon_menu_black);
                    mToolbar.setLogo(R.mipmap.icon_home_logo_black);
                    isLightMode = false;
                    refreshStatusBarMode();
                    mTopSearchRootView.setVisibility(View.GONE);
                } else {
                    //中间状态

                }
            }
        });
    }

    /**
     * 刷新状态栏颜色
     */
    private void refreshStatusBarMode() {
        //设置状态栏字体颜色
        if (null == getActivity()) return;
        if (isLightMode)
            StatusBarUtils.setLightMode(getActivity().getWindow());
        else
            StatusBarUtils.setDarkMode(getActivity().getWindow());
    }

    /**
     * 初始化tab列表
     */
    private void initTabList() {
        LinearLayoutManager tabLayout = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);
        mRecyclerTab.setLayoutManager(tabLayout);
        mRecyclerTab.setAdapter(mCategoryTabListAdapter);

        //Tab切换选中项事件处理
        mCategoryTabListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mCategoryTabListAdapter.setSelectedIndex(position);
                mViewPager.setCurrentItem(position);
            }
        });

        //ViewPager切换选中项事件处理
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCategoryTabListAdapter.setSelectedIndex(position);
                mRecyclerTab.scrollToPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onRefreshPageSuccess(List list, boolean isLastPage) {

    }

    @Override
    public void onLoadMorePageSuccess(List list, boolean isLastPage) {

    }

    @Override
    public void showCategoryList(List<CategoryItemResponseBean> list) {
        EasyLog.Companion.getDEFAULT().d("展示Tab " + list);
        mCategoryTabListAdapter.setNewData(list);

        //设置新闻页面
        List<HomeLearnViewPagerItemFragment> fragmentList = new ArrayList<>();
        for (CategoryItemResponseBean model : list) {
            HomeLearnViewPagerItemFragment itemFragment = new HomeLearnViewPagerItemFragment();
            itemFragment.setArguments(HomeLearnViewPagerItemFragment.buildIntentData(model.category_id));
            fragmentList.add(itemFragment);
        }
        HomeLearnViewPagerAdapter viewPagerAdapter = new HomeLearnViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.setNewData(fragmentList);
        mViewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void showBannerList(List<BannerItemResponseBean> list) {
        EasyLog.Companion.getDEFAULT().d("展示Banner " + list);
        hideLoadingView();
        if (null == list) return;
        mBanner.setAdapter(new HomeBannerImageAdapter(list))
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new RectangleIndicator(getContext()))//设置指示器
                .setOnBannerListener((data, position) -> {
                    Snackbar.make(mBanner, ((BannerItemResponseBean) data).getTitle(), Snackbar.LENGTH_SHORT).show();
                    LogUtils.d("position：" + position);
                });
    }
}
