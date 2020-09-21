package com.dyh.common.lib.mvp.impl

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import butterknife.ButterKnife
import butterknife.Unbinder
import com.dyh.common.lib.BaseApplication
import com.dyh.common.lib.mvp.IPageMultipleStatusView
import com.dyh.common.lib.mvp.MVPDispatcher
import com.dyh.common.lib.mvp.MVPPresenter
import com.dyh.common.lib.mvp.MVPView
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar.OnTitleBarListener
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


/**
 * 提供的BaseActivity封装示例。如果需要使用集成库中的mvp模块。可以仿照此部分代码进行对应的封装，
 *
 * @author haoge on 2018/5/30
 */
abstract class BaseMVPActivity : RxAppCompatActivity(), MVPView, IPageMultipleStatusView {

    // 一个Activity持有一个唯一的Dispatcher派发器。
    private val mvpDispatcher = MVPDispatcher()
    // 一个Activity持有一个唯一的model做基础交互展示
    val viewModel by lazy { MVPViewImpl(this, this) }
    private var butterUnbinder: Unbinder? = null
    //是否需要展示多状态视图，默认展示
    private var isNeedShowMultipleStatusView = true;

    // 然后在对应生命周期进行派发
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BaseApplication.getInstance().addActivity(this);

        val layoutId = getLayoutId()
        if (layoutId != 0) {
            setContentView(layoutId)
            butterUnbinder = ButterKnife.bind(this)
        }

        val titleBar: CommonTitleBar? = getCommonTitleBar()

        titleBar?.setListener(OnTitleBarListener { v, action, extra -> onTitleBarClicked(v, action, extra) })

        initPage(savedInstanceState)



        createPresenters()?.forEach { mvpDispatcher.addPresenter(it) }

        mvpDispatcher.dispatchOnCreate(intent?.extras)
    }

    protected open fun onTitleBarClicked(v: View?, action: Int, extra: String?) {
        if (action == CommonTitleBar.ACTION_LEFT_TEXT ||
                action == CommonTitleBar.ACTION_LEFT_BUTTON) {
            onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()
        mvpDispatcher.dispatchOnStart()
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle<Long>())
                .subscribe()
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

    override fun onRestart() {
        super.onRestart()
        mvpDispatcher.dispatchOnRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
        mvpDispatcher.dispatchOnDestroy()
        butterUnbinder?.unbind()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mvpDispatcher.dispatchOnActivityResult(requestCode, resultCode, data)
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        mvpDispatcher.dispatchOnSaveInstanceState(outState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mvpDispatcher.dispatchOnSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        mvpDispatcher.dispatchOnRestoreInstanceState(savedInstanceState)
    }

    // 交由统一的交互器进行统一样式展示
    override fun getHostActivity() = this

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
        val intent = Intent(this, clazz)
        startActivity(intent)
    }

    /**
     * startActivity with bundle
     *
     * @param clazz
     * @param bundle
     */
    protected fun readyGo(clazz: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, clazz)
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
        val intent = Intent(this, clazz)
        if (null != model) {
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_KEY, model)
            intent.putExtra(INTENT_KEY, bundle)
        }
        startActivity(intent)
    }

    /**
     * 传递model 默认传的Boolean
     * @return
     */
    open fun getTransModels(): Any? {
        var obj: Any = Boolean::class.java
        val intent = intent
        if (intent != null) {
            val bundle = intent.getBundleExtra(INTENT_KEY)
            if (bundle != null) {
                obj = bundle.getParcelable(BUNDLE_KEY)
            }
        }
        return obj
    }


    /**
     * startActivity then finish
     *
     * @param clazz
     */
    protected fun readyGoThenKill(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
        finish()
    }

    /**
     * startActivity with bundle then finish
     *
     * @param clazz
     * @param bundle
     */
    protected fun readyGoThenKill(clazz: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
        finish()
    }

    /**
     * startActivityForResult
     *
     * @param clazz
     * @param requestCode
     */
    protected fun readyGoForResult(clazz: Class<*>, requestCode: Int) {
        val intent = Intent(this, clazz)
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
        val intent = Intent(this, clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivityForResult(intent, requestCode)
    }

    open fun closeKeyboard() {
        val view = window.peekDecorView()
        if (view != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun setNeedShowMultipleStatusView(isNeed: Boolean) {
        this.isNeedShowMultipleStatusView = isNeed;
    }
}