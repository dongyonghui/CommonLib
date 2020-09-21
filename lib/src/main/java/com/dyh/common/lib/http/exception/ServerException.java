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

package com.dyh.common.lib.http.exception;

/**
 * <p>描述：处理服务器异常</p>
 * 作者： zhouyou<br>
 * 日期： 2016/9/15 16:51 <br>
 * 版本： v1.0<br>
 */
public class ServerException extends RuntimeException {
    private int errCode;
    private String message;
    private String status;

    public ServerException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
        this.message = msg;
    }

    public ServerException(String status, int errCode, String message) {
        this.errCode = errCode;
        this.message = message;
        this.status = status;
    }

    public int getErrCode() {
        return errCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}