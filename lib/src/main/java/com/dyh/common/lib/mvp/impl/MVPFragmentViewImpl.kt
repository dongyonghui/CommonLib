package com.dyh.common.lib.mvp.impl

import android.app.Activity
import android.text.TextUtils
import androidx.fragment.app.Fragment
import com.dyh.common.lib.R
import com.dyh.common.lib.easy.EasyToast
import com.dyh.common.lib.mvp.IPageMultipleStatusView
import com.dyh.common.lib.mvp.MVPView
import com.dyh.common.lib.safe.SafeDialogHandle
import com.dyh.common.lib.weigit.MultipleStatusView
import com.dyh.common.lib.weigit.dialog_default.MyLoadingDialog

/**
 * 为了使Activity与其绑定的所有Fragment均享有同样的基础展示逻辑。
 * 故在此提供此基础实现类。自动绑定给使用的Fragment/Activity进行使用
 * @author haoge on 2018/8/31
 */
class MVPFragmentViewImpl(private val activity: Activity, private val fragment: Fragment, private val pageMultipleStatusView: IPageMultipleStatusView) : MVPView {
    // 加载中的提示Dialog
    @Suppress("DEPRECATION")
    private val progressDialog: MyLoadingDialog by lazy {
        val dialog = MyLoadingDialog(activity)
        dialog.setMessage(activity.getString(R.string.lib_loading))
        return@lazy dialog
    }
    private val mMultipleStatusView: MultipleStatusView? by lazy {
        if (0 == pageMultipleStatusView.getMultipleStatusContentViewId()) {
            return@lazy null;
        }
        var tempView = MultipleStatusView.attach(fragment, pageMultipleStatusView.getMultipleStatusContentViewId());
        tempView.setOnRetryClickListener {
            pageMultipleStatusView.onRetryClick()
        }
        return@lazy tempView
    }
    private val toast = EasyToast.DEFAULT

    // 基础实现。
    override fun getHostActivity() = activity

    fun getHostFragment() = fragment

    override fun showLoadingView(massage: String?) {
        if (pageMultipleStatusView.isNeedShowMultipleStatusView())
            mMultipleStatusView?.showLoading(massage ?: activity.getString(R.string.lib_loading))

        pageMultipleStatusView.getCommonTitleBar()?.showCenterProgress()
        //如果没有标题栏也没有多状态布局，则弹框加载
        if (null == mMultipleStatusView && null == pageMultipleStatusView.getCommonTitleBar()) {
            progressDialog.setMessage(massage)
            SafeDialogHandle.safeShowDialog(progressDialog)
        }
    }

    override fun showNetworkErrorView(massage: String?) {
        hideLoadingView(false)
        if (pageMultipleStatusView.isNeedShowMultipleStatusView())
            mMultipleStatusView?.showNoNetwork(massage)
    }

    override fun showErrorView(massage: String?) {
        hideLoadingView(false);
        if (!TextUtils.isEmpty(massage)) {
            EasyToast.DEFAULT.show(message = massage);
        }
        if (pageMultipleStatusView.isNeedShowMultipleStatusView())
            mMultipleStatusView?.showError(massage)
    }

    override fun showEmptyView(massage: String?) {
        hideLoadingView(false)
        if (pageMultipleStatusView.isNeedShowMultipleStatusView())
            mMultipleStatusView?.showEmpty(massage)
    }

    override fun hideLoadingView() {
        hideLoadingView(true);
    }

    private fun hideLoadingView(isShowContent: Boolean) {
        if (isShowContent) {
            mMultipleStatusView?.showContent()
        }
        pageMultipleStatusView.getCommonTitleBar()?.dismissCenterProgress()
        //如果没有标题栏也没有多状态布局，则弹框加载
        if (null == mMultipleStatusView && null == pageMultipleStatusView.getCommonTitleBar()) {
            SafeDialogHandle.safeDismissDialog(progressDialog)
        }
    }

    override fun toastMessage(message: String) = toast.show(message)
    override fun toastMessage(resId: Int) = toast.show(resId)
}