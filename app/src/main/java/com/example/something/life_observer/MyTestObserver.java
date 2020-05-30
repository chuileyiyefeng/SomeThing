package com.example.something.life_observer;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MyTestObserver implements LifecycleObserver {
    private static final String TAG = "TAG";

    /**
     * 使用注解  @OnLifecycleEvent 来表明该方法需要监听指定的生命周期事件
     * 当Activity执行了onResume()时，该方法会被自动调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.e(TAG, "onResume: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.e(TAG, "onPause: ");
    }

}
