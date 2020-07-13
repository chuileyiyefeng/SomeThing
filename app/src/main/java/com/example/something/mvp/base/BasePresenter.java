package com.example.something.mvp.base;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M extends BaseModel, V extends BaseView, CONTRACT> {
    protected M m;
    private WeakReference<V> reference;

    public BasePresenter() {
        m = getM();
    }

    public abstract M getM();

    void bindView(V v) {
        reference = new WeakReference<>(v);
    }

    public abstract CONTRACT getContract();

    public V getView() {
        if (reference != null) {
            return reference.get();
        }
        return null;
    }

    void unBindView() {
        if (reference != null) {
            reference.clear();
            reference = null;
            System.gc();
        }
    }
}
