package com.example.something;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.something.mvp.MvpActivity;
import com.example.something.net_work.TestNetWorkActivity;
import com.example.something.utils.StatusBarUtil;

import org.jetbrains.annotations.NotNull;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setStatusBarColor(this, R.color.white);
        StatusBarUtil.setStatusTextDark(this, true);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.tv_net, R.id.tv_mvp,R.id.tv_rx_java})
    public void onClick(@NotNull View v) {
        switch (v.getId()) {
            case R.id.tv_net:
                startActivity(new Intent(this, TestNetWorkActivity.class));
                break;
            case R.id.tv_kotlin:
                break;
            case R.id.tv_mvp:
                startActivity(new Intent(this, MvpActivity.class));
                break;
            case R.id.tv_rx_java:
                startActivity(new Intent(this, TestRxJavaActivity.class));
                break;
        }
    }








}
