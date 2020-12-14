package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nhz.doctorapp.R
import com.nhz.doctorapp.delegates.HomeDelegates
import com.nhz.shared.data.vos.SpecialitiesVO

class SpecialitiesViewHolder(itemView : View,val mDelegate : HomeDelegates) : BaseViewHolder<SpecialitiesVO>(itemView) {

    init {
        itemView.setOnClickListener {
            mData?.let {
                mDelegate.onTapSpeciality(it,false)
            }
        }
    }

    override fun bindData(data: SpecialitiesVO) {
        mData = data

        Glide.with(itemView.context)
                .load(data.icon)
                .into(itemView.findViewById(R.id.ivSpeciality))

        itemView.findViewById<TextView>(R.id.tvSpeciality).text = data.name
    }
}