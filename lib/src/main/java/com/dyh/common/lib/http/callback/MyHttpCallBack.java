/*
 * Copyright (C) 2017 zhouyou(478319399@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dyh.common.lib.http.callback;

import com.dyh.common.lib.http.exception.ApiException;
import com.dyh.common.lib.http.model.ResponseOptional;
import com.dyh.common.lib.mvp.MVPView;

/**
 * <p>描述：处理了网络请求加载框自动展示隐藏,不用关注其他回调方法</p>
 * 使用该回调默认只需要处理onError，onSuccess两个方法既成功失败<br>
 * 作者： Allan<br>
 */
public abstract class MyHttpCallBack<T> extends CallBack<ResponseOptional<T>> {

    protected MVPView mView;

    public MyHttpCallBack(MVPView mView) {
        this.mView = mView;
    }

    @Override
    public void onStart() {
        mView.showLoadingView(null);
    }

    @Override
    public void onError(ApiException e) {
        mView.showErrorView(e.getMessage());
    }

    @Override
    public void onSuccess(ResponseOptional<T> tResponseOptional) {
        mView.hideLoadingView();
    }

    @Override
    public void onCompleted() {
        mView.hideLoadingView();
    }
}
