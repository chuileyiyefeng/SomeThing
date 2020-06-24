package com.example.something;

import android.util.Log;

import com.example.something.net_work.GetRequestInterface;
import com.example.something.net_work.Translation;
import com.example.something.net_work.Translation2;
import com.example.something.net_work.base.BaseActivity;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestRxJavaActivity extends BaseActivity {
    @Override
    protected int bindLayout() {
        return R.layout.activity_rx_java;
    }

    @Override
    protected void initView() {
        initObservable();
    }


    void initObservable() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();

        GetRequestInterface request = retrofit.create(GetRequestInterface.class);
        

        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("内存中");
                emitter.onComplete();
            }
        });
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("网络中");
            }
        });

        Observable<String> observable3 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("磁盘中");
            }
        });
        Observable.merge(observable,observable2,observable3).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: "+s );
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
//        Observable.concat(observable,
//                observable2, observable3)
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(String integer) {
//                        Log.e(TAG, "onNext: " + "多个观察者串行:" + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        // 事件开始延迟 发送次数 第一次发送开始延迟 每次发送事件的时间间隔
//        Observable.merge(Observable.intervalRange(0,3,4,3, TimeUnit.SECONDS),
//                Observable.intervalRange(10,3,0,3,TimeUnit.SECONDS))
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        Log.e(TAG, "onNext: " + "多个观察者按发送时间串行:" + aLong);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        // 对多个被观察者中的事件进行合并处理
//        Observable.zip(Observable.just(1, 2), Observable.just("嘻嘻", "哈哈", "呵呵"), new BiFunction<Integer, String, String>() {
//
//            @Override
//            public String apply(Integer integer, String s) throws Exception {
//                return integer + s;
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.e(TAG, "onNext: " + s);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
//        Observable.combineLatest(Observable.just(1, 2, 3), Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS), new BiFunction<Integer, Long, Long>() {
//            @Override
//            public Long apply(Integer integer, Long aLong) {
//                Log.e(TAG, "apply: 合并数据：" + integer + " " + aLong);
//                return integer + aLong;
//            }
//        }).subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Long aLong) {
//                Log.e(TAG, "合并结果: " + aLong);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });


        //  前2个数据聚合，然后与后1个数据继续进行聚合
//        Observable.just(1, 2, 3, 4).reduce(new BiFunction<Integer, Integer, Integer>() {
//            @Override
//            public Integer apply(Integer integer, Integer integer2) throws Exception {
//                Log.e(TAG, "apply: " + (integer + integer2));
//                return integer + integer2;
//            }
//        }).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                Log.e(TAG, "accept: " + integer);
//            }
//        });

        // 将被观察者Observable发送的数据事件收集到一个数据结构里
//        Observable.just(1, 2, 3, 4, 5, 6).collect(new Callable<ArrayList<Integer>>() {
//            @Override
//            public ArrayList<Integer> call() throws Exception {
//                return new ArrayList<>();
//            }
//        }, new BiConsumer<ArrayList<Integer>, Integer>() {
//            @Override
//            public void accept(ArrayList<Integer> integers, Integer integer) throws Exception {
//                        integers.add(integer);
//            }
//        }).subscribe(new Consumer<ArrayList<Integer>>() {
//            @Override
//            public void accept(ArrayList<Integer> integers) throws Exception {
//                Log.e(TAG, "accept: "+integers.toString() );
//            }
//        }).dispose();

        // 在一个被观察者发送事件前，追加发送一些数据 / 一个新的被观察者
//        Observable.just(1, 2, 3, 4)
//                .startWith(0)
//                .startWithArray(5, 6, 7, 8, 9)
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.e(TAG, "onNext: " + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
        // 统计被观察者发送事件的数量 返回结果 = Long类型
//        Observable.just(1, 2, 3, 4)
//                .startWith(0)
//                .startWithArray(5, 6, 7, 8, 9)
//                .count()
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        Log.e(TAG, "accept: " + aLong);
//                    }
//                }).dispose();

    }
}