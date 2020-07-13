package com.example.something.mvp.login;

import com.example.something.mvp.base.BaseEntity;

public interface LoginContract {
    interface M {
        // model层子类完成方法的具体实现
        void login(String name, String password);
    }

    interface V<T extends BaseEntity> {

        void loginResult(T t);
    }

    interface Present<T extends BaseEntity> {
        void login(String name, String password);

        void loginResult(T t);
    }
}
