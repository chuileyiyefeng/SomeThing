package com.example.something.mvp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.something.R;
import com.example.something.mvp.base.BaseView;
import com.example.something.mvp.login.LoginContract;
import com.example.something.mvp.login.LoginPresent;
import com.example.something.mvp.model.ImageBean;
import com.example.something.mvp.model.LoginBean;
import com.example.something.mvp.utils.LitePalUtils;

import org.litepal.LitePal;

public class MvpActivity extends BaseView<LoginPresent, LoginContract.V> {
    EditText etName;
    EditText etPwd;
    TextView tvLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LitePal.getDatabase();
        setContentView(R.layout.activity_mvp_test);
        tvLogin = findViewById(R.id.tv_click);
        etName = findViewById(R.id.etName);
        etPwd = findViewById(R.id.etPwd);
        LoginBean bean = new LitePalUtils<LoginBean>().getFirstData(LoginBean.class);
        if (bean!=null) {
            etName.setText(bean.getName());
            etPwd.setText(bean.getPassword());
        }
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.getContract().login(etName.getText().toString(), etPwd.getText().toString());
            }
        });
        // length =10   9 8 7 6 5 4 3 2 1 0  98 87 76 65 54 32 21 10   8 7 6 5 4 3 2 1 0 9
        // i=0  9 外循环  第一次
        // 0 8 // 内循环


//        tvLogin.setOnClickListener(v -> p.getContract().login(etName.getText().toString(), etPwd.getText().toString()));
    }

    @Override
    public LoginPresent createPresenter() {
        return new LoginPresent();
    }

    @Override
    public LoginContract.V getContract() {
        return (LoginContract.V<LoginBean>) bean -> {
            Log.e("success", "loginResult: " + "success");
            bean.save();
            Toast.makeText(MvpActivity.this, "登录成功  " + "数据是否保存——" + bean.isSaved(), Toast.LENGTH_SHORT).show();
        };
    }
}
