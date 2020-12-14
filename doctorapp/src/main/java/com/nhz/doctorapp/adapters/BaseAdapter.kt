package com.nhz.doctorapp.adapters

import androidx.recyclerview.widget.RecyclerView
import com.nhz.doctorapp.views.viewholders.BaseViewHolder

abstract class BaseAdapter<T,W : BaseViewHolder<T>> : RecyclerView.Adapter<W>() {

    var mData : MutableList<T> = mutableListOf()

    override fun getItemCount(): Int {
        return mData.count()
    }

    override fun onBindViewHolder(holder: W, position: Int) {
        holder.bindData(mData[position])
    }

    fun addNewData(data : MutableList<T>){
        mData = data
        notifyDataSetChanged()
    }

    fun getData() : List<T>{
        return mData
    }

}