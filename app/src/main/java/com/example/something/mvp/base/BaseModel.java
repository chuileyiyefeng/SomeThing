package com.example.something.mvp.base;

public abstract class BaseModel<P extends BasePresenter, CONTRACT> {
    public P p;

    public BaseModel(P p) {
        this.p = p;
    }

    protected abstract CONTRACT getContract();
}
