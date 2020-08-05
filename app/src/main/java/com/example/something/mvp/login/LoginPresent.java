package com.example.something.mvp.login;

import android.util.Log;

import com.example.something.mvp.MvpActivity;
import com.example.something.mvp.base.BasePresenter;
import com.example.something.mvp.model.ImageBean;
import com.example.something.mvp.model.LoginBean;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class LoginPresent extends BasePresenter<LoginModel, MvpActivity, LoginContract.Present> {

    @Override
    public LoginModel getM() {
        return new LoginModel(this);
    }

    @Override
    public LoginContract.Present getContract() {
        return new LoginContract.Present<LoginBean>() {
            @Override
            public void login(String name, String password) {
                // P层的方法实现有三种思路

                // 1 方法交给M层，自己只做转发
//                m.getContract().login(name, password);


                //  2 P层自己处理
                List<LoginBean> beans = DataSupport.findAll(LoginBean.class);
                LoginBean beanData;
                if (beans != null && beans.size() > 0) {
                    beanData = beans.get(0);
                    if (name.equals(beanData.name) && password.equals(beanData.password)) {
                        LoginBean bean = new LoginBean();
                        bean.setName(name);
                        bean.setPassword(password);
                        loginResult(bean);
                    } else {
                        Log.e("Login", "login: " + "登录失败");
                    }
                }

                // 3 让功能模块去做 比如这里是网络请求

            }

            @Override
            public void loginResult(LoginBean bean) {
                getView().getContract().loginResult(bean);
            }
        };
    }
}
