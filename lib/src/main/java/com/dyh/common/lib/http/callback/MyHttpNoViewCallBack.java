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

/**
 * <p>描述：无需关心加载框和多状态视图等UI操作的可以使用此回调,不用关注其他回调方法</p>
 * 作者： Allan<br>
 */
public abstract class MyHttpNoViewCallBack<T> extends com.dyh.common.lib.http.callback.CallBack<ResponseOptional<T>> {

    public MyHttpNoViewCallBack() {
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onError(ApiException e) {
    }


    @Override
    public void onCompleted() {
    }
}
