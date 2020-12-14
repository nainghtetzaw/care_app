package com.nhz.doctorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhz.doctorapp.R
import com.nhz.doctorapp.delegates.HomeDelegates
import com.nhz.doctorapp.views.viewholders.SpecialitiesViewHolder
import com.nhz.shared.data.vos.SpecialitiesVO

class SpecialitiesAdapter(delegate : HomeDelegates) : BaseAdapter<SpecialitiesVO,SpecialitiesViewHolder>() {

    private val mDelegate : HomeDelegates = delegate

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialitiesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_specialities,parent,false)
        return SpecialitiesViewHolder(view,mDelegate)
    }
}