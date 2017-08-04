package com.micky.kotlinproject.common.view

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import com.micky.kotlinproject.R
import com.micky.kotlinproject.common.utils.ViewUtils
import kotlinx.android.synthetic.main.view_permission_dialog.*

/**
 * Created by Administrator on 2017/7/27.
 */
class PermissionDialog:Dialog, View.OnClickListener {

    var cancelAble = true
    var permissionDialogListener:PermissionDialogListener? = null

    constructor(context: Context?) : super(context) {
        init()
    }
    constructor(context: Context?, themeResId: Int) : super(context, themeResId) {
        init()
    }
    constructor(context: Context?, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener) {
        init()
    }
    constructor(context: Context?, cancelable: Boolean, cancelListener: ((dialog: DialogInterface) -> Unit)?) : super(context, cancelable, cancelListener) {
        init()
    }

    fun init() {
        var view = LayoutInflater.from(context).inflate(R.layout.view_permission_dialog, null)
        view.setOnClickListener(this)
        setContentView(view)
        tv_cancel.setOnClickListener(this)
        tv_setting.setOnClickListener(this)
        setCanceledOnTouchOutside(true)

        window.attributes.width = ViewUtils.getScreenInfo(context).widthPixels - ViewUtils.dip2px(context, 25.0F) * 2
    }

    override fun setCancelable(flag: Boolean) {
        super.setCancelable(flag)
        cancelAble = flag
    }

    fun setTip(resId: Int) {
        tv_tip.setText(resId)
    }

    fun setTip(text: String) {
        tv_tip.setText(text)
    }

    override fun onClick(v: View?) {
        if (cancelAble) {
            dismiss()
        }
        when(v?.id) {
            R.id.tv_cancel -> permissionDialogListener?.onCancel()
            R.id.tv_setting -> permissionDialogListener?.onSetting()
        }
    }

    interface PermissionDialogListener {
        fun onCancel()
        fun onSetting()
    }
}