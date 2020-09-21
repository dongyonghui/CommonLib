package com.dyh.common.lib.mvp.impl

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import butterknife.Unbinder
import com.dyh.common.lib.mvp.IPageMultipleStatusView
import com.dyh.common.lib.mvp.MVPDispatcher
import com.dyh.common.lib.mvp.MVPPresenter
import com.dyh.common.lib.mvp.MVPView
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar
import com.trello.rxlifecycle2.components.support.RxFragment

/**
 * 提供的BaseFragment封装示例。如果需要使用集成库中的mvp模块。可以仿照此部分代码进行对应的封装，
 * @author haoge on 2018/8/31
 */
abstract class BaseMVPFragment : RxFragment(), MVPView, IPageMultipleStatusView {

    // 一个Activity持有一个唯一的Dispatcher派发器。
    private val mvpDispatcher = MVPDispatcher()
    lateinit var viewModel: MVPFragmentViewImpl
    private var butterUnbinder: Unbinder? = null
    //是否需要展示多状态视图，默认展示
    private var isNeedShowMultipleStatusView = true;

    /**
     * 视图是否已经初初始化
     */
    protected var isInit = false
    protected var isLoad = false
    protected var isFirstLoad = true

    // =====初始化页面=======
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(getLayoutId(), container, false)
        butterUnbinder = ButterKnife.bind(this, root)
        return root
    }

    /**
     * 视图销毁的时候讲Fragment是否初始化的状态变为false
     */
    override fun onDestroyView() {
        super.onDestroyView()
        isInit = false
        isLoad = false
    }

    /**
     * 视图是否已经对用户可见，系统的方法
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        isCanLoadData()
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    open fun isCanLoadData() {
        if (!isInit) {
            return
        }
        if (userVisibleHint) {
            lazyLoad(isFirstLoad)
            isFirstLoad = false;
            isLoad = true
        } else {
            if (isLoad) {
                stopLoad()
            }
        }
    }

    protected open fun stopLoad() {

    }

    protected open fun lazyLoad(isFirst: Boolean) {

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPage(savedInstanceState)

        isInit = true
        /**初始化的时候去加载数据**/
        isCanLoadData()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onAttachToContext(context)
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        //小于api小于23，只走此方法，onAttach(context: Context?) 不执行
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity)
        }
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        //小于api小于23，只走此方法，onAttach(context: Context?) 不执行
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity)
        }
    }

    fun onAttachToContext(context: Context?) {
        // 当host activity为BaseMVPActivity时。则统一使用activity自身的viewModel。做到样式统一
        viewModel = MVPFragmentViewImpl(context as Activity, this, this)
        createPresenters()?.forEach { mvpDispatcher.addPresenter(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mvpDispatcher.dispatchOnCreate(savedInstanceState)
        // 对于fragment来说。需要
        savedInstanceState?.let { mvpDispatcher.dispatchOnRestoreInstanceState(savedInstanceState) }
    }

    override fun onStart() {
        super.onStart()
        mvpDispatcher.dispatchOnStart()
    }

    override fun onResume() {
        super.onResume()
        mvpDispatcher.dispatchOnResume()
    }

    override fun onPause() {
        super.onPause()
        mvpDispatcher.dispatchOnPause()
    }

    override fun onStop() {
        super.onStop()
        mvpDispatcher.dispatchOnStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mvpDispatcher.dispatchOnDestroy()
        butterUnbinder?.unbind()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mvpDispatcher.dispatchOnSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mvpDispatcher.dispatchOnActivityResult(requestCode, resultCode, data)
    }

    // 交由统一的交互器进行统一样式展示
    override fun getHostActivity() = viewModel.getHostActivity()

    override fun isNeedShowMultipleStatusView(): Boolean {
        return isNeedShowMultipleStatusView;
    }

    override fun getCommonTitleBar(): CommonTitleBar? = null
    override fun showLoadingView(massage: String?) = viewModel.showLoadingView(massage)
    override fun hideLoadingView() = viewModel.hideLoadingView()
    override fun showEmptyView(massage: String?) = viewModel.showEmptyView(massage)
    override fun showErrorView(massage: String?) = viewModel.showErrorView(massage)
    override fun showNetworkErrorView(massage: String?) = viewModel.showNetworkErrorView(massage)
    override fun toastMessage(message: String) = viewModel.toastMessage(message)
    override fun toastMessage(resId: Int) = viewModel.toastMessage(resId)
    override fun onRetryClick() {

    }

    /**
     * 指定使用的LayoutID，用于进行setContentView操作。当return 0时，则代表不使用
     */
    abstract fun getLayoutId(): Int
    //扫描工具界面 确定按钮是否可用设置

    abstract fun initPage(savedInstanceState: Bundle?)
    /**
     * 继承此方法，提供需要与此页面相绑定的Presenter, 可绑定多个Presenter
     */
    open fun createPresenters(): Array<out MVPPresenter<*>>? = null

    /**
     * 如果需要在页面内展示多状态，可复写此方法，
     * 返回需要在loading中隐藏并替换未加载状态组件的id即可
     * 注意：返回的id必须是此Activity中的组件
     */
    override fun getMultipleStatusContentViewId(): Int = 0

    /**
     * startActivity
     *
     * @param clazz
     */
    protected fun readyGo(clazz: Class<*>) {
        val intent = Intent(getContext(), clazz)
        startActivity(intent)
    }

    /**
     * startActivity with bundle
     *
     * @param clazz
     * @param bundle
     */
    protected fun readyGo(clazz: Class<*>, bundle: Bundle?) {
        val intent = Intent(getContext(), clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    /**
     * model传递
     */
    val INTENT_KEY = "intent_key"
    val BUNDLE_KEY = "bundle_key"
    protected fun readyGoModel(clazz: Class<*>, model: Parcelable) {
        val intent = Intent(getContext(), clazz)
        if (null != model) {
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_KEY, model)
            intent.putExtra(INTENT_KEY, bundle)
        }
        startActivity(intent)
    }

    /**
     * startActivityForResult
     *
     * @param clazz
     * @param requestCode
     */
    protected fun readyGoForResult(clazz: Class<*>, requestCode: Int) {
        val intent = Intent(getContext(), clazz)
        startActivityForResult(intent, requestCode)
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    fun readyGoForResult(clazz: Class<*>, requestCode: Int, bundle: Bundle?) {
        val intent = Intent(getContext(), clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivityForResult(intent, requestCode)
    }


    fun setNeedShowMultipleStatusView(isNeed: Boolean) {
        this.isNeedShowMultipleStatusView = isNeed;
    }
}