package com.example.something;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.something.activity.EditSomeThingActivity;
import com.example.something.activity.SelectImageActivity;
import com.example.something.activity.TimeSelectActivity;
import com.example.something.kotlin_test.KotlinActivity;
import com.example.something.mvp.MvpActivity;
import com.example.something.net_work.TestNetWorkActivity;
import com.example.something.net_work.base.BaseActivity;

import org.jetbrains.annotations.NotNull;
import org.weishe.baselibrary.utils.ImageSelectUtils;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
    }

    @OnClick({R.id.tv_net, R.id.tv_mvp, R.id.tv_rx_java,
            R.id.tv_photo, R.id.tv_kotlin, R.id.tv_photo_system,
            R.id.tv_input, R.id.tv_time})
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
                ImageSelectUtils.getInstance().setMaxPhoto(9).setSelectResult(strings -> Toast.makeText(this, "selectResult : " + strings.get(0), Toast.LENGTH_SHORT).show()).start(this);
                break;
            case R.id.tv_photo_system:
                startActivity(new Intent(this, SelectImageActivity.class));
                break;
            case R.id.tv_input:
                startActivity(new Intent(this, EditSomeThingActivity.class));
                break;
            case R.id.tv_time:
                startActivity(new Intent(this, TimeSelectActivity.class));
                break;
        }
    }


}
