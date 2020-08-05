package com.example.something.net_work;

import com.example.something.net_work.base.BaseObserver;
import com.example.something.net_work.base.BasePresenter;
import com.example.something.net_work.base.BaseResponse;
import com.example.something.net_work.bean.Article;

import java.util.ArrayList;

public class ArticlePresenter extends BasePresenter<ArticleView> {
    ArticlePresenter(ArticleView baseView) {
        super(baseView);
    }

    void getData() {
        addDisposable(apiServer.getList(), new BaseObserver<ArrayList<Article>>(getBaseView()) {
            @Override
            public void onSuccess(ArrayList<Article> o) {
                getBaseView().onSuccess(o);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
