package com.example.something.net_work.base;

import com.example.something.net_work.bean.Article;

import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiUrl {
    @GET(ConstantUrl.list)
    Observable<BaseResponse<ArrayList<Article>>> getList();

    @POST("monitor")
    Observable<String> post(@Body RequestBody body);
}
