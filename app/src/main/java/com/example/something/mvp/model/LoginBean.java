package com.example.something.mvp.model;

import com.example.something.mvp.base.BaseEntity;

public class LoginBean extends BaseEntity {
    public String name;
    public String password;


    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
