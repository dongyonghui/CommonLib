package com.dyh.common.lib.update_app;

import com.dyh.common.lib.dw.util.MathUtil;

import java.io.Serializable;

/**
 * 版本信息
 */
public class UpdateAppBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String updateTitle;//更新对话框标题
    private String version;//最新版本号
    private String downloadUrl;//下载地址
    private String versionMinimum;//最低版本号
    private String versionName;//版本名称
    private String appSize;//应用大小
    private String content;//更新内容
    private int currentVersion;//当前版本号
    //服务器端的原生返回数据（json）,方便使用者在hasNewApp自定义渲染dialog的时候可以有别的控制，比如：#issues/59
    private String origin_res;
    /**********以下是内部使用的数据**********/

    //网络工具，内部使用
    private HttpManager httpManager;
    private String targetPath;
    private boolean mHideDialog;
    private boolean mShowIgnoreVersion;
    private boolean mDismissNotificationProgress;
    private boolean mOnlyWifi;

    //是否隐藏对话框下载进度条,内部使用
    public boolean isHideDialog() {
        return mHideDialog;
    }

    public void setHideDialog(boolean hideDialog) {
        mHideDialog = hideDialog;
    }

    public boolean isUpdate() {
        return MathUtil.getIntegerNumber(version) > currentVersion;
    }

    public HttpManager getHttpManager() {
        return httpManager;
    }

    public String getVersion() {
        return version;
    }

    public UpdateAppBean setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public UpdateAppBean setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
        return this;
    }

    public String getUpdateTitle() {
        return updateTitle;
    }

    public UpdateAppBean setUpdateTitle(String updateTitle) {
        this.updateTitle = updateTitle;
        return this;
    }

    public String getVersionMinimum() {
        return versionMinimum;
    }

    public UpdateAppBean setVersionMinimum(String versionMinimum) {
        this.versionMinimum = versionMinimum;
        return this;
    }

    public String getVersionName() {
        return versionName;
    }

    public UpdateAppBean setVersionName(String versionName) {
        this.versionName = versionName;
        return this;
    }

    public String getAppSize() {
        return appSize;
    }

    public UpdateAppBean setAppSize(String appSize) {
        this.appSize = appSize;
        return this;
    }

    public String getContent() {
        return content;
    }

    public UpdateAppBean setContent(String content) {
        this.content = content;
        return this;
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public UpdateAppBean setCurrentVersion(int currentVersion) {
        this.currentVersion = currentVersion;
        return this;
    }

    public void setHttpManager(HttpManager httpManager) {
        this.httpManager = httpManager;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public boolean isConstraint() {
        return MathUtil.getIntegerNumber(versionMinimum) > currentVersion;
    }

    public boolean isShowIgnoreVersion() {
        return mShowIgnoreVersion;
    }

    public void showIgnoreVersion(boolean showIgnoreVersion) {
        mShowIgnoreVersion = showIgnoreVersion;
    }

    public void dismissNotificationProgress(boolean dismissNotificationProgress) {
        mDismissNotificationProgress = dismissNotificationProgress;
    }

    public boolean isDismissNotificationProgress() {
        return mDismissNotificationProgress;
    }

    public boolean isOnlyWifi() {
        return mOnlyWifi;
    }

    public void setOnlyWifi(boolean onlyWifi) {
        mOnlyWifi = onlyWifi;
    }

    public String getOriginRes() {
        return origin_res;
    }

    public UpdateAppBean setOriginRes(String originRes) {
        this.origin_res = originRes;
        return this;
    }

}
