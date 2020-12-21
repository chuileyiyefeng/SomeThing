package com.example.something.net_work.base

import com.example.something.net_work.bean.Article
import retrofit2.http.GET
import java.util.ArrayList

/**
 *  create by pan yi on 2020/10/28
 *  desc :
 */
interface ApiUrl2 {
    @GET(ConstantUrl.list)
    fun getList(): BaseResponse<ArrayList<Article>>
}