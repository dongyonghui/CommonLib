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

package com.dyh.common.lib.http.model;

import com.dyh.common.lib.http.EasyHttp;

/**
 * <p>描述：提供的默认的标注返回api</p>
 * 作者： zhouyou<br>
 * 日期： 2017/5/15 16:58 <br>
 * 版本： v1.0<br>
 */
public class ApiResult<T> {
    private String error_code;
    private String status;
    private String error_msg;
    private T result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isOk() {
        return EasyHttp.STATUS_SUCCESS.equalsIgnoreCase(getStatus());
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "errCode='" + error_code + '\'' +
                ", errMessage='" + error_msg + '\'' +
                ", data=" + result +
                '}';
    }
}
