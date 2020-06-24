package com.example.something.net_work.base;

import com.example.something.net_work.utils.RetrofitUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@SuppressWarnings("ALL")
public class BasePresenter<V extends BaseView> {
    private CompositeDisposable disposable;
    public V baseView;


    protected ApiUrl apiServer = RetrofitUtils.getApiUrl();

    public BasePresenter(V baseView) {
        this.baseView = baseView;
    }

    /**
     * 解除绑定
     */
    public void detachView() {
        baseView = null;
        removeDisposable();
    }

    /**
     * 返回 view
     */
    public V getBaseView() {
        return baseView;
    }

    public void addDisposable(Observable<?> observable, BaseObserver observer) {
        if (disposable == null) {
            disposable = new CompositeDisposable();
        }
        disposable.add(observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(observer));
    }

    private void removeDisposable() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

}
