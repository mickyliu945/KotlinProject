package com.micky.kotlinproject.common.utils

import android.content.Context
import com.micky.kotlinproject.common.view.LoadingDialog
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/30 16:43
 */
abstract class RxAsync<Params, Progress, Result>(val context: Context) {

    var loadingDialog: LoadingDialog? = null
    var isShowDialog: Boolean = false

    constructor(context: Context, isShowDialog: Boolean) : this(context) {
        this.isShowDialog = isShowDialog
    }

    fun execute(vararg params: Params): Disposable {
        showDialog()
        onPreExecute()
        return Flowable.create(FlowableOnSubscribe<Result> { emitter ->
            var result = doInBackground(*params)
            if (result != null) {
                emitter.onNext(result)
            } else {
                emitter.onError(RxNullPointerException())
            }
            emitter.onComplete()
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> this@RxAsync.onPostExecute(result) },
                        { throwable ->
                            hideDialog()
                            if (throwable is RxNullPointerException) {
                                this@RxAsync.onPostExecute(null)
                            } else {
                                this@RxAsync.onError(throwable)
                            }
                        },
                        { hideDialog(); this@RxAsync.onComplete() })
    }

    protected fun onPreExecute() {

    }

    abstract fun doInBackground(vararg params: Params): Result?

    open fun onPostExecute(result: Result?) {

    }

    open fun onError(t: Throwable) {

    }

    open fun onComplete() {

    }

    fun showDialog() {
        if (isShowDialog && loadingDialog == null) {
            loadingDialog = LoadingDialog.create(context)
        }
        loadingDialog?.show()
    }

    fun hideDialog() {
        if (loadingDialog != null && loadingDialog!!.isShowing) {
            loadingDialog?.dismiss()
        }
    }

    class RxNullPointerException : NullPointerException() {}
}