package com.dyh.android.winehall.http;

import android.text.TextUtils;

import com.dyh.android.winehall.entity.local.SPAuthEntity;
import com.dyh.common.lib.easy.EasyLog;
import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.http.model.HttpHeaders;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：Allan
 * 时间：2019/9/16/016
 * 联系方式：648731994@qq.com
 * 描述：自定义网络请求头部拦截器，添加token
 */
public class MyHeaderTokenInterceptor implements Interceptor {
    private HttpHeaders headers;

    public MyHeaderTokenInterceptor(HttpHeaders headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        SPAuthEntity spAuthEntity = EasySharedPreferences.load(SPAuthEntity.class);
        if (!TextUtils.isEmpty(spAuthEntity.topsession))
            headers.headersMap.put("topsession", spAuthEntity.topsession);

        if (headers.headersMap.isEmpty())
            return chain.proceed(builder.build());

        try {
            for (Map.Entry<String, String> entry : headers.headersMap.entrySet()) {
                builder.header(entry.getKey(), entry.getValue()).build();
            }
            EasyLog.Companion.getDEFAULT().d("请求头部消息：" + headers.headersMap);
        } catch (Exception e) {
            EasyLog.Companion.getDEFAULT().e(e);
        }
        return chain.proceed(builder.build());

    }
}
