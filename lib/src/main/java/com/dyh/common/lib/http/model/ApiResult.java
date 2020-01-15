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

/**
 * <p>描述：提供的默认的标注返回api</p>
 * 作者： zhouyou<br>
 * 日期： 2017/5/15 16:58 <br>
 * 版本： v1.0<br>
 */
public class ApiResult<T> {
    private int errCode;
    private String errMessage;
    private T data;
    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isOk() {
        return errCode == 0;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "errCode='" + errCode + '\'' +
                ", errMessage='" + errMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
