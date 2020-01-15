package com.dyh.commonlib.http;

import com.dyh.commonlib.entity.local.SPEntity;
import com.dyh.common.lib.easy.EasyLog;
import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.http.model.HttpHeaders;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：DongYonghui
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
        SPEntity spEntity = EasySharedPreferences.load(SPEntity.class);
        if (spEntity.loginResponseBean != null) {
            headers.headersMap.put("Gouku-Token", spEntity.loginResponseBean.getToken());
        }
        if (headers.headersMap.isEmpty()) return chain.proceed(builder.build());
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
