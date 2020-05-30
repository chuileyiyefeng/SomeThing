package com.example.something.net_work.utils;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NullInterceptor implements Interceptor {
    final String TAG = "InterceptorNull";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
//                .newBuilder()
//                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//                .addHeader("Accept-Encoding", "gzip, deflate")
//                .addHeader("Connection", "keep-alive")
//                .addHeader("Accept", "*/*")
//                .addHeader("Cookie", "add cookies here")
//                .build()
                ;

//        Log.e(TAG, "request:" + request.toString());
//        long t1 = System.nanoTime();
        Response response = chain.proceed(chain.request());
//        long t2 = System.nanoTime();
//        Log.e(TAG, String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
//                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Log.e(TAG, "response body:" + content);
        return response.newBuilder()
                .body(ResponseBody.create(mediaType, content))
//                .header("Authorization", Your.sToken)
                .build();
    }
}
