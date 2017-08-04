package com.micky.kotlinproject.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.micky.kotlinproject.R
import com.micky.kotlinproject.common.utils.inflate
import com.micky.kotlinproject.domain.model.City
import com.micky.kotlinproject.presenter.CityPresenter

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/31 23:03
 */
class CityAdapter : BaseRVAdapter<City, CityAdapter.ItemViewHolder>() {

    var cityPresenter: CityPresenter? = null

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var city = getItem(position)
        holder.tvName.text = city?.name
        holder.tvParentName.text = city?.parentName
        holder.cbSel.isChecked = city?.selected ?: false
        holder.itemView.setOnClickListener {
            city?.selected = !(city != null && city?.selected)
            cityPresenter?.selectCity(city!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder? {
        var view = parent.context.inflate(R.layout.item_city)
        val lp = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        view.layoutParams = lp
        return ItemViewHolder(view)
    }

    inner class ItemViewHolder(itemView: View) : BaseItemViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_name)
        var tvParentName: TextView = itemView.findViewById(R.id.tv_parent_name)
        var cbSel: CheckBox = itemView.findViewById(R.id.cb_sel)
    }
}