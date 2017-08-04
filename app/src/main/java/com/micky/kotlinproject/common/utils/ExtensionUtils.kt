package com.micky.kotlinproject.common.utils

import android.content.Context
import android.content.res.AssetManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.micky.kotlinproject.R
import com.micky.kotlinproject.common.view.toast.Toasty
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.nio.charset.Charset

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/27 17:00
 */
fun Context.getAppName(): String {
    return this.getString(R.string.app_name)
}

fun <T> Observable<T>.applyCommonSchedulers(): Observable<T> {
    return subscribeOn(Schedulers.io()).
            unsubscribeOn(Schedulers.io()).
            observeOn(AndroidSchedulers.mainThread())
}

fun <T> Flowable<T>.applyCommonSchedulers(): Flowable<T> {
    return subscribeOn(Schedulers.io()).
            unsubscribeOn(Schedulers.io()).
            observeOn(AndroidSchedulers.mainThread())
}

fun Context.showToastInfo(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toasty.info(this, text, duration).show()
}

fun Context.showToastInfo(resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toasty.info(this, this.getString(resId), duration).show()
}

fun Context.showToastWarning(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toasty.warning(this, text, duration).show()
}

fun Context.showToastWarning(resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toasty.warning(this, this.getString(resId), duration).show()
}

fun Context.showToastError(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toasty.warning(this, text, duration).show()
}

fun Context.showToastError(resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toasty.warning(this, this.getString(resId), duration).show()
}

fun Context.showToastSuccess(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toasty.warning(this, text, duration).show()
}

fun Context.showToastSuccess(resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toasty.warning(this, this.getString(resId), duration).show()
}

fun View.visibility() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun AssetManager.fileAsString(filePath: String): String {
    return open(filePath).use {
        it.readBytes().toString(Charset.defaultCharset())
    }
}

fun Context.inflate(layoutId: Int, parent: ViewGroup? = null): View {
    return LayoutInflater.from(this).inflate(layoutId, parent, false)
}
