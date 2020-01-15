package com.dyh.common.lib.mvp.impl

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder
import com.dyh.common.lib.BaseApplication
import com.dyh.common.lib.mvp.MVPDispatcher
import com.dyh.common.lib.mvp.MVPPresenter
import com.dyh.common.lib.mvp.MVPView
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar


/**
 * 提供的BaseActivity封装示例。如果需要使用集成库中的mvp模块。可以仿照此部分代码进行对应的封装，
 *
 * @author haoge on 2018/5/30
 */
abstract class BaseMVPActivity : AppCompatActivity(), MVPView {

    // 一个Activity持有一个唯一的Dispatcher派发器。
    private val mvpDispatcher = MVPDispatcher()
    // 一个Activity持有一个唯一的model做基础交互展示
    val viewModel by lazy { MVPViewImpl(this) }
    private var butterUnbinder: Unbinder? = null

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
        titleBar?.setListener { v, action, extra ->
            if (action == CommonTitleBar.ACTION_LEFT_TEXT) {
                onBackPressed()
            }
        }

        initPage(savedInstanceState)



        createPresenters()?.forEach { mvpDispatcher.addPresenter(it) }

        mvpDispatcher.dispatchOnCreate(intent?.extras)
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

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mvpDispatcher.dispatchOnSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        mvpDispatcher.dispatchOnRestoreInstanceState(savedInstanceState)
    }

    // 交由统一的交互器进行统一样式展示
    override fun getHostActivity() = this

    override fun showLoadingDialog() {
        if (getCommonTitleBar() == null) {
            viewModel.showLoadingDialog()
        } else {
            getCommonTitleBar()?.showCenterProgress()
        }
    }

    override fun hideLoadingDialog() {
        if (getCommonTitleBar() == null) {
            viewModel.hideLoadingDialog()
        } else {
            getCommonTitleBar()?.dismissCenterProgress()
        }
    }

    override fun toastMessage(message: String) = viewModel.toastMessage(message)
    override fun toastMessage(resId: Int) = viewModel.toastMessage(resId)

    /**
     * 指定公用的标题栏，展示loading动画使用
     */
    abstract fun getCommonTitleBar(): CommonTitleBar?

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
}