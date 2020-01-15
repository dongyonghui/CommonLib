package com.dyh.common.lib.mvp.impl

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.dyh.common.lib.mvp.MVPDispatcher
import com.dyh.common.lib.mvp.MVPPresenter
import com.dyh.common.lib.mvp.MVPView
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar

/**
 * 提供的BaseFragment封装示例。如果需要使用集成库中的mvp模块。可以仿照此部分代码进行对应的封装，
 * @author haoge on 2018/8/31
 */
abstract class BaseMVPFragment : Fragment(), MVPView {

    // 一个Activity持有一个唯一的Dispatcher派发器。
    private val mvpDispatcher = MVPDispatcher()
    lateinit var viewModel: MVPViewImpl
    private var butterUnbinder: Unbinder? = null

    // =====初始化页面=======
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(getLayoutId(), container, false)
        butterUnbinder = ButterKnife.bind(this, root)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPage(savedInstanceState)
    }

    // =====绑定presenter并进行生命周期派发=======
    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        //小于api小于23，只走此方法，onAttach(context: Context?) 不执行
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        onAttachToContext(context)
    }

    fun onAttachToContext(context: Context?) {
        // 当host activity为BaseMVPActivity时。则统一使用activity自身的viewModel。做到样式统一
        viewModel = when (context) {
            is BaseMVPActivity -> context.viewModel
            else -> MVPViewImpl(context as Activity)
        }

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

    /** 指定使用的LayoutID，用于进行setContentView操作。当return 0时，则代表不使用*/
    abstract fun getLayoutId(): Int

    abstract fun initPage(savedInstanceState: Bundle?)
    /** 继承此方法，提供需要与此页面相绑定的Presenter, 可绑定多个Presenter*/
    open fun createPresenters(): Array<out MVPPresenter<*>>? = null

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
}