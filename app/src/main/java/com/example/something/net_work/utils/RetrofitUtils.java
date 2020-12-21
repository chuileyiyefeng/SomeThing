package com.example.something.net_work.utils;

import com.example.something.net_work.base.ApiUrl;
import com.example.something.net_work.base.ConstantUrl;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static ApiUrl apiUrl;

    public static ApiUrl getApiUrl() {

        if (apiUrl == null) {
            synchronized (RetrofitUtils.class) {
                apiUrl = new RetrofitUtils().initRetrofit().create(ApiUrl.class);
            }
        }
        return apiUrl;
    }

    private Retrofit initRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(ConstantUrl.baseUrl)
                .client(initClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private OkHttpClient initClient() {
        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
                .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0)
                .allEnabledCipherSuites()
                .build();
        // 兼容http接口
        ConnectionSpec spec1 = new ConnectionSpec.Builder(ConnectionSpec.CLEARTEXT).build();

        return new OkHttpClient().newBuilder()

                .readTimeout(ConstantUrl.DEFAULT_TIME, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(ConstantUrl.DEFAULT_TIME, TimeUnit.SECONDS)//设置请求超时时间
                .writeTimeout(ConstantUrl.DEFAULT_TIME, TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(new LogInterceptor())//添加打印拦截器
//                .addInterceptor(new NullInterceptor())
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .connectionSpecs(Arrays.asList(spec, spec1))// 解决https报错问题
                .build();
    }
}
