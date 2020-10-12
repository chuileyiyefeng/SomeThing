package com.example.something;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.something.activity.SelectImageActivity;
import com.example.something.kotlin_test.KotlinActivity;
import com.example.something.mvp.MvpActivity;
import com.example.something.net_work.TestNetWorkActivity;
import com.example.something.utils.StatusBarUtil;

import org.jetbrains.annotations.NotNull;
import org.weishe.baselibrary.utils.ImageSelectUtils;

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


    @OnClick({R.id.tv_net, R.id.tv_mvp, R.id.tv_rx_java, R.id.tv_photo, R.id.tv_kotlin, R.id.tv_photo_system, R.id.tv_input})
    public void onClick(@NotNull View v) {
        switch (v.getId()) {
            case R.id.tv_net:
                startActivity(new Intent(this, TestNetWorkActivity.class));
                break;
            case R.id.tv_kotlin:
                startActivity(new Intent(this, KotlinActivity.class));
                break;
            case R.id.tv_mvp:
                startActivity(new Intent(this, MvpActivity.class));
                break;
            case R.id.tv_rx_java:
                startActivity(new Intent(this, TestRxJavaActivity.class));
                break;
            case R.id.tv_photo:
                ImageSelectUtils.getInstance().setMaxPhoto(9).setSelectResult(strings -> {
                    ImageView iv = findViewById(R.id.iv_select);
                    Glide.with(MainActivity.this).load(strings.get(0)).into(iv);
                    Log.e("selectResult", "selectResult: " + strings);
                }).start(this);
                break;
            case R.id.tv_photo_system:
                startActivity(new Intent(this, SelectImageActivity.class));
                break;
            case R.id.tv_input:

                break;
        }
    }


}
