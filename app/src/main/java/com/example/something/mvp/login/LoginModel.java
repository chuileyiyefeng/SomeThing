package com.example.something.mvp.login;

import com.example.something.mvp.base.BaseModel;
import com.example.something.mvp.model.ImageBean;

public class LoginModel extends BaseModel<LoginPresent, LoginContract.M> {

    LoginModel(LoginPresent loginPresent) {
        super(loginPresent);
    }

    @Override
    protected LoginContract.M getContract() {
        return new LoginContract.M() {
            @Override
            public void login(String name, String password) {
                // 这里假设有网络连接操作
                if (name.equals("haha")&&password.equals("123")) {
                    p.getContract().loginResult(new ImageBean());
                }
            }
        };
    }
}
