package com.example.something.net_work.base;

public interface BaseView {
    void showLoading();

    void hideLoading();

    void onErrorCode(BaseResponse bean);
}
