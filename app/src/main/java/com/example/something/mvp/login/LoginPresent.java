package com.example.something.mvp.login;

import com.example.something.mvp.MvpActivity;
import com.example.something.mvp.base.BasePresenter;
import com.example.something.mvp.model.ImageBean;

public class LoginPresent extends BasePresenter<LoginModel, MvpActivity, LoginContract.Present> {

    @Override
    public LoginModel getM() {
        return new LoginModel(this);
    }

    @Override
    public LoginContract.Present getContract() {
        return new LoginContract.Present<ImageBean>() {
            @Override
            public void login(String name, String password) {
                // P层的方法实现有三种思路

                // 1 方法交给M层，自己只做转发
                m.getContract().login(name, password);


                //  2 P层自己处理
                if (name.equals("haha") && password.equals("123")) {
                    loginResult(new ImageBean());
                }

                // 3 让功能模块去做 比如这里是网络请求

            }

            @Override
            public void loginResult(ImageBean bean) {
                getView().getContract().loginResult(bean);
            }
        };
    }
}
