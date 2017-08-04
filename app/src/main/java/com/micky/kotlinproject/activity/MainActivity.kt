package com.micky.kotlinproject.activity

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.micky.kotlinproject.R
import com.micky.kotlinproject.common.rxbus.RxBus
import com.micky.kotlinproject.common.rxpermissions.PermissionRequest
import com.micky.kotlinproject.common.utils.*
import com.micky.kotlinproject.common.view.LoadingDialog
import com.micky.kotlinproject.domain.model.City
import com.micky.kotlinproject.domain.network.ServiceManager
import com.micky.kotlinproject.domain.network.TestService
import com.micky.kotlinproject.domain.network.response.WeatherResponse
import com.micky.kotlinproject.event.EventType
import com.micky.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*
import com.micky.kotlinproject.common.rxbus.RxBusEvent
import com.micky.kotlinproject.common.rxbus.Subscribe
import com.micky.kotlinproject.common.rxbus.ThreadMode




/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/25 22:30
 */
class MainActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tip = getString(R.string.permission_sd, getAppName(), getAppName())
        var permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        var permissionRequest = PermissionRequest(this, tip, permissions)
        permissionRequest.permissionListener = object : PermissionRequest.PermissionListener {
            override fun onGrant() {
                FileUtils.initBaseFile()
                LogUtils.initLogger(Constants.BASE_FILE_PATH, FileUtils.LOG_PATH, FileUtils.LOG_FILE)
                initView()
            }
        }
        permissionRequest.requestPermission(true)

        RxBus.getDefault().register(this)
    }

    fun initView() {
        btn_retrofit.setOnClickListener(this)
        btn_shared_preferences.setOnClickListener(this)
        btn_rxbus.setOnClickListener(this)
        btn_recycler_view.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        RxBus.getDefault().unregister(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_shared_preferences -> testSharedPreferences()
            R.id.btn_retrofit -> testRetrofit()
            R.id.btn_rxbus -> textRxBus()
            R.id.btn_recycler_view -> testRecyclerView()
        }
    }

    fun testSharedPreferences() {
        var city = City("1001", "成都", "chengdu", "四川", "100")
        var cityStr = StringUtils.objectToString(city)
        SPUtils.putOjbect(this, "as", cityStr)
        SPUtils.putOjbect(this, "ai", 2)
        SPUtils.putOjbect(this, "ab", true)
        SPUtils.putOjbect(this, "af", 2.5F)
        SPUtils.putOjbect(this, "al", 2L)

        var cityString: String = SPUtils.getObject(this, "as", "")
        var citySaved = StringUtils.stringToObject(cityString, City::class.java)
        var i: Int? = SPUtils.getObject(this, "ai", 0)
        var b: Boolean? = SPUtils.getObject(this, "ab", false)
        var f: Float? = SPUtils.getObject(this, "af", 0F)
        var l: Long? = SPUtils.getObject(this, "al", 0)
        showToastInfo("$citySaved \n $i $b $f $l")
    }

    fun testRetrofit() {
        var loadingDialog = LoadingDialog.create(this);
//        ServiceManager.createStringService(TestService::class.java)
//                .getWeather("101010100")
//                .applyCommonSchedulers()
//                .subscribe(
//                        { string: String -> showToastInfo(string) },
//                        { t: Throwable -> Logger.e(t.message, t) })

        ServiceManager.createGsonService(TestService::class.java)
                .getWeather2("101010100")
                .applyCommonSchedulers()
                .doOnSubscribe {
                    loadingDialog.show()
                }.doOnComplete {
                    loadingDialog.dismiss()
                }.subscribe(
                        { response: WeatherResponse ->
                            var weatherInfo = response?.weatherinfo
                            if (weatherInfo != null) {
                                showToastInfo(weatherInfo.toString())
                                Logger.t("MainActivity").i(weatherInfo.toString())
                            } else {
                                Logger.t("MainActivity").e("weatherInfo info is null")
                            }
                        },
                        { t: Throwable -> Logger.e(t.message, t) })
    }

    fun textRxBus() {
        var city = City("1001", "成都", "chengdu", "四川", "100")
//        RxBus.getDefault().send(EventType.TYPE_TEST)
        RxBus.getDefault().send(EventType.TYPE_TEST, city)
    }

    fun testRecyclerView() {
        startActivity(Intent(this, CityListActivity::class.java))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onBusEvent(event: RxBusEvent) {
        when (event.what) {
            EventType.TYPE_TEST -> showToastInfo(event.data.toString())
        }
    }
}