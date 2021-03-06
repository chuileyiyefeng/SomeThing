package com.example.something.net_work.base;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.something.R;
import com.example.something.net_work.LoadListener;
import com.example.something.net_work.RecyclerLoadMoreListener;
import com.example.something.utils.StatusBarUtil;

import org.weishe.baselibrary.adapter.BaseAdapter;

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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        StatusBarUtil.setStatusBarColor(this, R.color.white);
        StatusBarUtil.setStatusTextDark(this, true);
        setContentView(LayoutInflater.from(this).inflate(getLayoutId(), null));
        ButterKnife.bind(this);
        presenter = createPresenter();
        initView();
        initData();
        getLifecycle().addObserver(presenter);
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
//        if (presenter != null) {
//            presenter.detachView();
//        }
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


    private void setEditNumberDecimal(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                //删除.后面超过两位的数字
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }

                //如果.在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }

                //如果起始位置为0并且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);

                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });
    }
}
