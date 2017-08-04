package com.micky.kotlinproject.common.view

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import com.micky.kotlinproject.R
import com.micky.kotlinproject.common.utils.inflate
import kotlinx.android.synthetic.main.view_loading.*

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/30 21:33
 */
class LoadingDialog : Dialog {

    private constructor(context: Context) : super(context)

    private constructor(context: Context, themeResId: Int) : super(context, themeResId) {
        var view = context.inflate(R.layout.view_loading, null)
        setContentView(view)
        setCanceledOnTouchOutside(false)
    }

    private constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener) : super(context, cancelable, cancelListener)

    companion object {
        fun create(context: Context): LoadingDialog {
            var dialog = LoadingDialog(context, R.style.LoadingDialogStyle)
            dialog.setText(R.string.loading)
            return dialog
        }
    }

    fun setText(text: String) {
        tv_loading.text = text
    }

    fun setText(resId: Int) {
        tv_loading.setText(resId)
    }

}