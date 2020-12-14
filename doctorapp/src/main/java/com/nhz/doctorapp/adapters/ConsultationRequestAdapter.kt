package com.nhz.doctorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhz.doctorapp.R
import com.nhz.doctorapp.delegates.ConsultationRequestDelegate
import com.nhz.doctorapp.views.viewholders.BaseViewHolder
import com.nhz.doctorapp.views.viewholders.NewConsultationRequestViewHolder
import com.nhz.doctorapp.views.viewholders.OldConsultationRequestViewHolder
import com.nhz.shared.data.vos.ConsultationRequestVO
import com.nhz.shared.data.vos.ConsultationsVO

class ConsultationRequestAdapter(delegate : ConsultationRequestDelegate) : RecyclerView.Adapter<BaseViewHolder<ConsultationRequestVO>>() {

    private val VIEW_TYPE_OLD = 1
    private val VIEW_TYPE_NEW = 2
    private val mDelegate : ConsultationRequestDelegate = delegate
    private var mData : MutableList<ConsultationRequestVO> = mutableListOf()

//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): NewConsultationRequestViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_new_consultation_request,parent,false)
//        return NewConsultationRequestViewHolder(view,mDelegate)
//    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : BaseViewHolder<ConsultationRequestVO> {
        when(viewType){
            VIEW_TYPE_OLD -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_old_consultation_request,parent,false)
                return OldConsultationRequestViewHolder(view,mDelegate)
            }
            VIEW_TYPE_NEW -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_new_consultation_request,parent,false)
                return NewConsultationRequestViewHolder(view,mDelegate)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_old_consultation_request,parent,false)
                return OldConsultationRequestViewHolder(view,mDelegate)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mData[position].old_or_new) {
            VIEW_TYPE_OLD
        }else VIEW_TYPE_NEW
    }


    override fun getItemCount(): Int {
        return mData.count()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ConsultationRequestVO>, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_OLD){
            holder.bindData(mData[position])
        }else {
            holder.bindData(mData[position])
        }
    }

    fun addNewData(data : MutableList<ConsultationRequestVO>){
        mData = data
        notifyDataSetChanged()
    }
}