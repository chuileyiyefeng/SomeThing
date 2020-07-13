package com.example.something.mvp.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseView<P extends BasePresenter, CONTRACT> extends AppCompatActivity {
    protected P p;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        p = createPresenter();
        p.bindView(this);
    }

    public abstract P createPresenter();

    public abstract CONTRACT getContract();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (p!=null) {
            p.unBindView();
        }
    }
}
