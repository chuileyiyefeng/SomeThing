package com.example.something.net_work;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface GetRequestInterface {
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
    Observable<Translation> getCall();

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
    Observable<Translation2> getCall2();
}
