package com.example.something.life_observer;

import androidx.lifecycle.LifecycleService;

public class MyLifeService extends LifecycleService {
    MyServiceObserver observer;

    public MyLifeService() {
        observer = new MyServiceObserver();
        getLifecycle().addObserver(observer);
    }
}
