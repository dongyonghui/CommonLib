package com.dyh.android.winehall.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dyh.android.winehall.MyApplication;
import com.dyh.android.winehall.R;
import com.dyh.android.winehall.constants.IntentConstants;
import com.dyh.android.winehall.entity.local.EventMessageBean;
import com.dyh.android.winehall.entity.local.SPUserEntity;
import com.dyh.android.winehall.entity.response.UserInfoResponseBean;
import com.dyh.android.winehall.presenter.MinePresenter;
import com.dyh.android.winehall.ui.mine.LoginActivity;
import com.dyh.android.winehall.ui.mine.MyAttachmentListActivity;
import com.dyh.android.winehall.view.IHomeMineView;
import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.glide.EasyGlide;
import com.dyh.common.lib.mvp.impl.BaseMVPFragment;
import com.dyh.common.lib.weigit.dialog_default.MyAppDialog;
import com.dyh.common.lib.weigit.dialog_default.interfaces.OnDialogClickListener;
import com.dyh.common.lib.weigit.refresh_layout.SmartRefreshLayout;
import com.dyh.common.lib.weigit.refresh_layout.api.RefreshLayout;
import com.dyh.common.lib.weigit.refresh_layout.listener.OnRefreshListener;
import com.dyh.common.lib.weigit.titlebar.statusbar.StatusBarUtils;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.Nullable;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：Allan
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/19/019 17:27
 * 描述：我的
 */
public class MineFragment extends BaseMVPFragment implements IHomeMineView {

    @BindView(R.id.mContentRootView)
    View mContentRootView;
    @BindView(R.id.mHeaderRootView)
    View mHeaderRootView;
    @BindView(R.id.mSmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.mHeadImageView)
    ImageView mHeadImageView;
    @BindView(R.id.mNickNameTextView)
    TextView mNickNameTextView;
    @BindView(R.id.mNickNameInfoTextView)
    TextView mNickNameInfoTextView;

    private final MinePresenter mMinePresenter = new MinePresenter(this);

    private boolean isStatusBarTrans;               //是否是透明标题栏（低版本手机不支持）

    /**
     * 构建传递的参数
     *
     * @param isStatusBarTrans
     * @return
     */
    public static MineFragment buildIntentData(boolean isStatusBarTrans) {
        MineFragment fragment = new MineFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(IntentConstants.IS_STATUS_BAR_TRANS, isStatusBarTrans);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_mine;
    }

    @Override
    public int getMultipleStatusContentViewId() {
        return R.id.mHeaderRootView;
    }

    @Override
    public void initPage(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (null != bundle) {
            isStatusBarTrans = bundle.getBoolean(IntentConstants.IS_STATUS_BAR_TRANS, false);
        }

        if (isStatusBarTrans) {
            //适配状态栏（首页交互需求，不能使用自适应布局，需要动态适配状态栏）
            mContentRootView.setPadding(mContentRootView.getPaddingLeft(), mContentRootView.getPaddingTop()
                            + MyApplication.getInstance().getStatusBarHeight()
                    , mContentRootView.getPaddingRight(), mContentRootView.getPaddingBottom());
        }

        //初始化页面的时候展示缓存中的用户信息
        refreshUserInfoFromCache();

        //设置下拉刷新
        mSmartRefreshLayout.setNoMoreData(false);
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mMinePresenter.refreshUserInfo();
            }
        });

        EventBus.getDefault().register(this);
    }

    @Override
    public void hideLoadingView() {
        super.hideLoadingView();
        mSmartRefreshLayout.finishRefresh();
        mSmartRefreshLayout.finishLoadMore();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessageBean event) {
        //登录成功后自动刷新
        if (EventMessageBean.CODE_LOGIN_SUCCESS == event.code) {
            //刷新详情数据
            refreshUserInfoFromCache();
        }
    }

    @Override
    protected void lazyLoad(boolean isFirst) {
        super.lazyLoad(isFirst);
        mMinePresenter.refreshUserInfo();
        //设置状态栏字体颜色
        if (null == getActivity()) return;
        StatusBarUtils.setDarkMode(getActivity().getWindow());
    }

    @Nullable
    @Override
    public CommonTitleBar getCommonTitleBar() {
        return null;
    }

    public void logout() {
        new MyAppDialog(getHostActivity()).setTitle(getString(R.string.app_name))
                .setMessage(R.string.hint_queryLogout)
                .setPositive(getString(R.string.common_confirm), new OnDialogClickListener() {
                    @Override
                    public void onDialogButtonClicked(MyAppDialog dialog) {
                        mMinePresenter.logout();
                    }
                })
                .setNegative(getString(R.string.dialog_cancel))
                .show();
    }

    @OnClick(R.id.mHeaderRootView)
    public void setUserInfo() {
        if (MyApplication.getInstance().isLogin())
            logout();
        else
            readyGo(LoginActivity.class);
    }

    @OnClick(R.id.mAttachmentTextView)
    public void showMyAttachmentList() {
        readyGo(MyAttachmentListActivity.class);
    }

    @Override
    public void showUserInfo2View(UserInfoResponseBean userInfo) {
        //如果用户信息为空，则清空界面数据
        if (null == userInfo) {
            mHeadImageView.setImageResource(R.mipmap.person_head_default);
            mNickNameTextView.setText(R.string.mine_login);
            mNickNameInfoTextView.setText(null);
            return;
        }
        //昵称
        mNickNameTextView.setText(userInfo.getNickname());
        //签名
        if (TextUtils.isEmpty(userInfo.getSignature()))
            mNickNameInfoTextView.setText(R.string.noSignInfo);
        else
            mNickNameInfoTextView.setText(userInfo.getSignature());
        //头像
        EasyGlide.loadCircleImage(getContext(), userInfo.getAvatar_show(), mHeadImageView);
    }

    private void refreshUserInfoFromCache() {
        SPUserEntity userEntity = EasySharedPreferences.load(SPUserEntity.class);
        showUserInfo2View(userEntity.userInfo);
    }
}
