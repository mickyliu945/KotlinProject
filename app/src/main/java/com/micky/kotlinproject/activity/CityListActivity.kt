package com.micky.kotlinproject.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.micky.kotlinproject.R
import com.micky.kotlinproject.adapter.BaseRVAdapter
import com.micky.kotlinproject.adapter.CityAdapter
import com.micky.kotlinproject.common.utils.showToastInfo
import com.micky.kotlinproject.domain.model.City
import com.micky.kotlinproject.presenter.CityPresenter
import com.micky.kotlinproject.presenter.impl.CityPresenterImpl
import kotlinx.android.synthetic.main.activity_city_list.*

/**
 * Created by Administrator on 2017/8/1.
 */
class CityListActivity : BaseActivity(), CityPresenter.CityView, BaseRVAdapter.OnRvItemClickListener<City, CityAdapter.ItemViewHolder> {

    lateinit var cityPresenter: CityPresenter
    lateinit var cityAdapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_list)

        cityPresenter = CityPresenterImpl(this, this)
        initView()
        cityPresenter.loadData()
    }

    fun initView() {
        rv_city.layoutManager = LinearLayoutManager(this)
        cityAdapter = CityAdapter()
        cityAdapter.onItemClickListener = this
        cityAdapter.cityPresenter = cityPresenter
        rv_city.adapter = cityAdapter
    }

    override fun onLoadData(cityList: MutableList<City>?) {
        cityAdapter.dataList = cityList
        cityAdapter.notifyDataSetChanged()
    }

    override fun onItemClick(adapter: BaseRVAdapter<City, CityAdapter.ItemViewHolder>, position: Int) {
        var city = cityAdapter.getItem(position)
        showToastInfo(city.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        cityPresenter?.onDestory()
    }

    override fun onSelectCitySuccess() {
        cityAdapter?.notifyDataSetChanged()
    }
}