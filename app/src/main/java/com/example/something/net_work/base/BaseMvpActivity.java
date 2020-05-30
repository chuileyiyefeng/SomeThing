package com.example.something.net_work.base;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.something.R;
import com.example.something.net_work.LoadListener;
import com.example.something.net_work.RecyclerLoadMoreListener;
import com.example.something.utils.StatusBarUtil;

import java.util.List;

import butterknife.ButterKnife;

@SuppressLint("Registered")
@SuppressWarnings("unchecked")
public abstract class BaseMvpActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected String TAG = "TAG " + getClass().getSimpleName();
    protected P presenter;

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StatusBarUtil.setStatusBarColor(this, R.color.white);
        StatusBarUtil.setStatusTextDark(this, true);
        setContentView(LayoutInflater.from(this).inflate(getLayoutId(), null));
        ButterKnife.bind(this);
        presenter = createPresenter();
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁时，解除绑定
        if (presenter != null) {
            presenter.detachView();
        }
    }

    protected void initListener() {
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    RecyclerView rvData;
    FrameLayout loadingView;
    SwipeRefreshLayout refreshLayout;
    BaseAdapter mAdapter;
    LinearLayoutManager manager;

    private void initListViews() {
        if (rvData == null) {
            manager = new LinearLayoutManager(this);
            rvData = findViewById(R.id.rv_data);
            rvData.setLayoutManager(manager);
            rvData.setAdapter(mAdapter);
            if (canLoadMore) {
                rvData.addOnScrollListener(new RecyclerLoadMoreListener() {
                    @Override
                    public void loadMore() {
                        if (loadListener != null) {
                            loadListener.loadMore();
                        }
                    }
                });
            }
            loadingView = findViewById(R.id.loading_view);
            refreshLayout = findViewById(R.id.sw_refresh);
            if (canRefreshing) {
                refreshLayout.setOnRefreshListener(() -> {
                    if (loadListener != null) {
                        loadListener.refreshing();
                    }
                });
            }
        }
    }

    boolean canRefreshing = true, canLoadMore = true;

    public void setLoadMode(boolean canRefreshing, boolean canLoadMore) {
        this.canRefreshing = canRefreshing;
        this.canLoadMore = canLoadMore;
    }


    // 加载数据
    protected void loadList(List list) {
        if (mAdapter == null) {
            return;
        }
        initListViews();
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            mAdapter.clearAllItem();
            refreshLayout.setRefreshing(false);
        }
        if (list == null) {
            return;
        }
        loadingView.setVisibility(View.GONE);
        mAdapter.addItem(list);
    }

    /**
     * 可以处理异常
     */
    @Override
    public void onErrorCode(BaseResponse bean) {
    }

    public void setLoadListener(BaseAdapter adapter, LoadListener loadListener) {
        mAdapter = adapter;
        this.loadListener = loadListener;
    }

    private LoadListener loadListener;

}
