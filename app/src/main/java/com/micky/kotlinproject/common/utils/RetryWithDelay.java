package com.micky.kotlinproject.common.utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2017/8/3.
 */

public class RetryWithDelay implements
        Function<Flowable<? extends Throwable>, Flowable<?>> {

    private final int maxRetries;
    private final int retryDelayMillis;
    private int retryCount;

    public RetryWithDelay(int maxRetries, int retryDelayMillis) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
    }

    @Override
    public Flowable<?> apply(@NonNull Flowable<? extends Throwable> flowable) throws Exception {
        return flowable
                .flatMap(new Function<Throwable, Flowable<?>>() {
                    @Override
                    public Flowable<?> apply(@NonNull Throwable throwable) throws
                            Exception {
                        if (++retryCount <= maxRetries) {
                            return Flowable.timer(retryDelayMillis,
                                    TimeUnit.MILLISECONDS);
                        }
                        // Max retries hit. Just pass the error along.
                        return Flowable.error(throwable);
                    }
                });
    }
}