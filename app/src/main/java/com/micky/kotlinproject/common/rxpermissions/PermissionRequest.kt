package com.micky.kotlinproject.common.rxpermissions

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import com.micky.kotlinproject.common.view.PermissionDialog
import com.micky.logger.Logger


/**
 * @Description 权限申请
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/25 23:35
 */
class PermissionRequest(val activity: AppCompatActivity, tip: String, val permissions: Array<String>) {

    val rxPermissions: RxPermissions
    val permissionDialog: PermissionDialog
    var permissionListener: PermissionRequest.PermissionListener? = null
    var ableFinishActivity: Boolean? = false

    constructor(activity: AppCompatActivity, tipResId: Int, permissions: Array<String>) : this(activity, activity.getString(tipResId), permissions)

    init {
        permissionDialog = PermissionDialog(activity)
        permissionDialog.setTip(tip)
        permissionDialog.permissionDialogListener = object : PermissionDialog.PermissionDialogListener {
            override fun onCancel() {
                if (ableFinishActivity == true) {
                    activity?.finish()
                }
            }

            override fun onSetting() {
                val intent = Intent()
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                if (Build.VERSION.SDK_INT >= 9) {
                    intent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
                    intent.data = Uri.fromParts("package", activity.getPackageName(), null)
                } else if (Build.VERSION.SDK_INT <= 8) {
                    intent.action = Intent.ACTION_VIEW
                    intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails")
                    intent.putExtra("com.android.settings.ApplicationPkgName", activity.getPackageName())
                }
                activity.startActivity(intent)
            }
        }
        rxPermissions = RxPermissions(activity)
    }

    /**
     * 申请权限
     * @param ableFinishActivity 是否关闭当前Activity
     * */
    fun requestPermission(ableFinishActivity: Boolean = false) {
        this.ableFinishActivity = ableFinishActivity
        rxPermissions.request(*permissions)
                .subscribe({ b: Boolean ->
                    if (b) {
                        permissionListener?.onGrant()
                    } else {
                        permissionDialog.show()
                    }
                }, { throwable: Throwable ->
                    Logger.e(throwable.message, throwable)
                })
    }

    interface PermissionListener {
        /**已授权 */
        fun onGrant()

        /**已禁止*/
        //void onForbid();
    }
}