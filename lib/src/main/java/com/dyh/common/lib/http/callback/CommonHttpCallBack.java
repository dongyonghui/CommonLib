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

import org.json.JSONException;
import org.json.JSONObject;

/**
 * <p>描述：简单的回调,默认可以使用该回调，不用关注其他回调方法</p>
 * 使用该回调默认只需要处理onError，onSuccess两个方法既成功失败<br>
 * 作者： Allan<br>
 * 日期： 2019年6月22日23:04:37<br>
 */
public abstract class CommonHttpCallBack<T> extends CallBack<T> {

    @Override
    public void onError(ApiException e) {
        onResponseError(e.getCode(), null);
    }

    @Override
    public void onSuccess(T s) {
        if (s instanceof String) {
            try {
                JSONObject jsonObject = new JSONObject((String) s);
                int errorCode = jsonObject.getInt("error");
                JSONObject resultJsonObject = jsonObject.getJSONObject("body");
                String resultJson = resultJsonObject.toString();
                if (0 == errorCode) {
                    onResponseOk(resultJson);
                } else {
                    onResponseError(errorCode, resultJson);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 响应正常数据
     *
     * @param json
     */
    public abstract void onResponseOk(String json);

    /**
     * 响应结果异常
     *
     * @param errorCode
     * @param json
     */
    public abstract void onResponseError(int errorCode, String json);

    @Override
    public void onStart() {
    }

    @Override
    public void onCompleted() {

    }
}
