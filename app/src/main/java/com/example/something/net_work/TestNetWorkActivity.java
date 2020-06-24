package com.example.something.net_work;

import com.example.something.R;
import com.example.something.net_work.base.BaseAdapter;
import com.example.something.net_work.base.BaseMvpActivity;
import com.example.something.net_work.bean.Article;

import java.util.ArrayList;

public class TestNetWorkActivity extends BaseMvpActivity<ArticlePresenter> implements ArticleView, LoadListener, BaseAdapter.ItemClick {

    ArticleAdapter adapter;

    @Override
    protected ArticlePresenter createPresenter() {
        return new ArticlePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_net_work;
    }


    @Override
    protected void initView() {
        adapter = new ArticleAdapter(this);
        setLoadListener(adapter, this);
        setLoadMode(true, true);
        adapter.addItemClick(this);
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

    @Override
    public void itemClick(int position) {

    }
}
