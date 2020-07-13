package com.example.something.mvp;

import com.example.something.mvp.model.ImageBean;

//view层交互，model层交互共同的请求（契约、合同类）
public interface DownloaderContract {
    interface M {
        //P层告诉Model层，需要做什么事情
        void request(ImageBean bean);
    }

    interface PV {
        // view层告诉P层需要做什么事情
        void request(ImageBean bean);

        // P层得到M层的结果返回，再通知V层
        void response(boolean isSuccess, ImageBean bean);
    }
}
