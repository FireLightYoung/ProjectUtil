package com.util.ming.projectutil.demo.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.util.ming.projectutil.R;
import com.util.ming.projectutil.activity.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ming on 17/5/9.
 */

public class RxJavaActivity extends BaseActivity implements View.OnClickListener {
    Button btn;
    String TAG = "ming";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        btn = (Button) findViewById(R.id.rxJava_start_btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rxJava_start_btn:
                task1();
                break;
        }
    }

    /**
     * 最简单的例子
     */
    public void task1() {
        //创建一个上游 Observable：
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "task1-Observable");
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        //创建一个下游 Observer
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "task1-subscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "task1-onNext-" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "task1-error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "task1-complete");
            }
        };
        //建立连接
        observable.subscribe(observer);
    }

    /**
     * 采用链型方法
     */
    public void task2() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "task2-Observable");
                emitter.onNext(1);
                emitter.onNext(2);
                //执行完此行代码，继续发送onNext(3),但观察者接收不到onNext(3)
                emitter.onComplete();
                //onError与onComplete唯一且互斥
                //  emitter.onError(new Throwable());
                emitter.onNext(3);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "task2-subscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "task2-onNext" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "task2-error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "task2-complete");
            }
        });
    }

    public void task3() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Log.d(TAG, "emit 3");
                emitter.onNext(3);
                Log.d(TAG, "emit complete");
                emitter.onComplete();
                Log.d(TAG, "emit 4");
                emitter.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            // 进行截断
            private Disposable mDisposable;
            private int i;

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "subscribe");
                //获取截断用的Disposable
                mDisposable = d;
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "onNext: " + value);
                i++;
                if (i == 2) {
                    Log.d(TAG, "dispose");
                    //截断
                    mDisposable.dispose();
                    Log.d(TAG, "isDisposed : " + mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "complete");
            }
        });
    }

    public void task4() {

    }
}
