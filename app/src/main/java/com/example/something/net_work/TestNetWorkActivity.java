package com.example.something.net_work;

import android.graphics.Rect;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.something.R;
import com.example.something.net_work.base.BaseMvpActivity;
import com.example.something.net_work.bean.Article;

import java.util.ArrayList;

public class TestNetWorkActivity extends BaseMvpActivity<ArticlePresenter> implements ArticleView, LoadListener {

    ArticleAdapter adapter;

    @Override
    protected ArticlePresenter createPresenter() {
        return new ArticlePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_net_work;
    }

    Rect rect;
    int distance;

    @Override
    protected void initView() {
        adapter = new ArticleAdapter(this);
        setLoadListener(adapter, this);
        setLoadMode(true, true);
    }

    @Override
    protected void initData() {
        initNet();
    }


    private void initNet() {
        presenter.getData();
    }

    @Override
    public void onSuccess(ArrayList<Article> list) {
        loadList(list);
    }


    @Override
    public void loadMore() {
        presenter.getData();
    }

    @Override
    public void refreshing() {
        presenter.getData();
    }
}
