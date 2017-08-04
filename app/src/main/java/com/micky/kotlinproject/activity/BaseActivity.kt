package com.micky.kotlinproject.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import com.micky.kotlinproject.common.utils.Constants

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/30 15:56
 */
open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var filter = IntentFilter(Constants.ACTION_LOGOUT)
        LocalBroadcastManager.getInstance(this).registerReceiver(exitReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (exitReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(exitReceiver)
        }
    }


    private val exitReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (Constants.ACTION_LOGOUT == intent.action) {
                finish()
            }
        }
    }
}