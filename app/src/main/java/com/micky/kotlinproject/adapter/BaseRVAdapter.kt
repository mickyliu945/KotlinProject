package com.micky.kotlinproject.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/31 22:29
 */
open class BaseRVAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    var dataList: MutableList<T>? = null

    var onItemClickListener: OnRvItemClickListener<T, VH>? = null

    fun clearData() {
        dataList?.clear()
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    fun getItem(position: Int): T? {
        return dataList?.get(position)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH? {
        return null
    }

    interface OnRvItemClickListener<T, VH : RecyclerView.ViewHolder> {
        fun onItemClick(adapter: BaseRVAdapter<T, VH>, position: Int)
    }

    open inner class BaseItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                this@BaseRVAdapter.onItemClickListener?.onItemClick(this@BaseRVAdapter, layoutPosition)
            }
        }
    }
}