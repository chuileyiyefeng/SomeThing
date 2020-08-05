package com.example.something.net_work.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.something.net_work.utils.RetrofitUtils;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@SuppressWarnings("ALL")
public class BasePresenter<V extends BaseView> implements LifecycleObserver {
    private CompositeDisposable disposable;
    private WeakReference<V> reference;

    protected ApiUrl apiServer = RetrofitUtils.getApiUrl();

    public BasePresenter(V baseView) {
        reference = new WeakReference<>(baseView);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        detachView();
    }

    /**
     * 解除绑定
     */
    public void detachView() {
        reference.clear();
        reference = null;
        removeDisposable();
    }

    /**
     * 返回 view
     */
    public V getBaseView() {
        return reference.get();
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
