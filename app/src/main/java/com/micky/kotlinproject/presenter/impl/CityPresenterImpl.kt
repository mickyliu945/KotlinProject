package com.micky.kotlinproject.presenter.impl

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.micky.kotlinproject.BaseApplication
import com.micky.kotlinproject.activity.BaseActivity
import com.micky.kotlinproject.common.utils.RxAsync
import com.micky.kotlinproject.common.utils.fileAsString
import com.micky.kotlinproject.domain.db.DBCore
import com.micky.kotlinproject.domain.model.City
import com.micky.kotlinproject.presenter.CityPresenter
import com.micky.logger.Logger

/**\
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/30 23:17
 */
class CityPresenterImpl(activity: BaseActivity) : BasePresenterImpl(activity), CityPresenter {
    var cityView: CityPresenter.CityView? = null

    constructor(activity: BaseActivity, cityView: CityPresenter.CityView) : this(activity) {
        this.cityView = cityView
    }

    override fun loadData() {
        addDisposable(object : RxAsync<Void, Void, MutableList<City>>(activity, true) {
            override fun doInBackground(vararg params: Void): MutableList<City>? {
                var cityList = DBCore.getInstance().database?.cityDao()?.queryAll()
                if (cityList == null || cityList.size <= 0) {
                    var json = context.assets.fileAsString("city.json")
                    cityList = GsonBuilder().create().fromJson<MutableList<City>>(json, object : TypeToken<List<City>>() {}.type)
                    DBCore.getInstance().database?.cityDao()?.insert(cityList)
                }
                return cityList
            }

            override fun onPostExecute(result: MutableList<City>?) {
                super.onPostExecute(result)
                cityView?.onLoadData(result)
            }

            override fun onError(t: Throwable) {
                super.onError(t)
                Logger.e(t, t.message)
            }

        }.execute())
    }

    override fun selectCity(city:City) {
        addDisposable(object : RxAsync<Void, Void, Void>(activity) {
            override fun doInBackground(vararg params: Void): Void? {
                DBCore.getInstance().database?.cityDao()?.update(city)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                cityView?.onSelectCitySuccess()
            }

            override fun onError(t: Throwable) {
                super.onError(t)
                Logger.e(t, t.message)
            }
        }.execute())
    }
}