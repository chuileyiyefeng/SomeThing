package com.example.something.view_model;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;


public class TestViewModel extends ViewModel {
    private int time;

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    private Handler mainHandler, workHandler;
    private HandlerThread handlerThread;

    public void start() {
        mainHandler = new Handler();
        handlerThread = new HandlerThread("TAG");
        handlerThread.start();
        workHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time++;
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null) {
                            listener.change(time + "s");
                        }
                        if (time < 100) {
                            go();
                        }
                    }
                });
            }
        };
        go();
    }

    private void go() {
        workHandler.sendEmptyMessage(0);
    }

    private ChangeListener listener;

    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }

    public interface ChangeListener {
        void change(String str);
    }
}
