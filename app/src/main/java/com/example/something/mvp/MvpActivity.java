package com.example.something.mvp;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.something.R;
import com.example.something.mvp.base.BaseView;
import com.example.something.mvp.login.LoginContract;
import com.example.something.mvp.login.LoginPresent;
import com.example.something.mvp.model.ImageBean;

public class MvpActivity extends BaseView<LoginPresent, LoginContract.V> {
    EditText etName;
    EditText etPwd;
    TextView tvLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_test);
        tvLogin = findViewById(R.id.tv_click);
        etName = findViewById(R.id.etName);
        etPwd = findViewById(R.id.etPwd);
        tvLogin.setOnClickListener(v -> p.getContract().login(etName.getText().toString(), etPwd.getText().toString()));
    }

    @Override
    public LoginPresent createPresenter() {
        return new LoginPresent();
    }

    @Override
    public LoginContract.V getContract() {
        return new LoginContract.V<ImageBean>() {
            @Override
            public void loginResult(ImageBean bean) {
                Log.e("success", "loginResult: " + "success");
                Toast.makeText(MvpActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            }
        };
    }

}
