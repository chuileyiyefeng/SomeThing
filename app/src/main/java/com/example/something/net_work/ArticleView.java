package com.example.something.net_work;

import com.example.something.net_work.base.BaseView;
import com.example.something.net_work.bean.Article;

import java.util.ArrayList;

public interface ArticleView extends BaseView {
    void onSuccess(ArrayList<Article> list);
}
