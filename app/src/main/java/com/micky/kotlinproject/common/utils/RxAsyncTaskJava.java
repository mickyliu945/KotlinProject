package com.micky.kotlinproject.common.utils;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/8/03 16:43
 */
public abstract class RxAsyncTaskJava<Params, Progress, Result> {

    public Disposable execute(final Params... params) {
        onPreExecute();
        return Flowable.create(new FlowableOnSubscribe<Result>() {
            @Override
            public void subscribe(FlowableEmitter<Result> emitter) throws Exception {
                Result result = doInBackground(params);
                if (result != null) {
                    emitter.onNext(result);
                } else {
                    emitter.onError(new RxNullPointerException());
                }
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                        RxAsyncTaskJava.this.onPostExecute(result);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (throwable instanceof RxNullPointerException) {
                            RxAsyncTaskJava.this.onPostExecute(null);
                        } else {
                            RxAsyncTaskJava.this.onError(throwable);
                        }
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        RxAsyncTaskJava.this.onComplete();
                    }
                });
    }

    protected void onPreExecute() {

    }

    protected abstract Result doInBackground(Params... params);

    protected void onPostExecute(Result result) {

    }

    protected void onError(Throwable t) {

    }

    protected void onComplete() {

    }

    static class RxNullPointerException extends NullPointerException{ }
}