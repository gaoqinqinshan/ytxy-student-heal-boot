package org.example.config;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Okhttp 配置
 */
public class OkhttpConfig {
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            //连接超时 设置
            .connectTimeout(30, TimeUnit.SECONDS)
            //读取超时
            .readTimeout(30, TimeUnit.SECONDS)
            //写超时
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();

}
