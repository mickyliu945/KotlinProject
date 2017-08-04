package com.micky.kotlinproject.presenter.impl

import com.micky.kotlinproject.activity.BaseActivity
import com.micky.kotlinproject.presenter.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/30 16:29
 */
open class BasePresenterImpl(val activity: BaseActivity) : BasePresenter {

    val compositeDisposable = CompositeDisposable()

    override fun onCreate() {

    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    /**请在Activity或Fragment销毁时调用，否则会内存溢出*/
    override fun onDestory() {
        compositeDisposable.clear()
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

}