package com.example.something.net_work;

import android.util.Log;

public class Translation2 {
    private int status;

    private content content;

    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

    //定义 输出返回数据 的方法
    public void show() {
        Log.e("RxJava2", content.out);
    }

}
