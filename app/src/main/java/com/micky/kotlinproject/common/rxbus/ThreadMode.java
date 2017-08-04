package com.micky.kotlinproject.common.rxbus;

public enum ThreadMode {
    /**
     * current thread
     */
    CURRENT_THREAD,

    /**
     * android main thread
     */
    MAIN,


    /**
     * new thread
     */
    NEW_THREAD
}
