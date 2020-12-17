package com.nhz.doctorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhz.doctorapp.R
import com.nhz.doctorapp.delegates.ChatMedicineDelegate
import com.nhz.doctorapp.views.viewholders.BaseViewHolder
import com.nhz.doctorapp.views.viewholders.ChatMedicineViewHolder
import com.nhz.doctorapp.views.viewholders.ReceiverMessageViewHolder
import com.nhz.doctorapp.views.viewholders.SenderMessageViewHolder
import com.nhz.shared.data.vos.LiveChatVO

class MessageListAdapter(delegate : ChatMedicineDelegate) : RecyclerView.Adapter<BaseViewHolder<LiveChatVO>>() {

    private var mDelegate : ChatMedicineDelegate = delegate
    private var mData : MutableList<LiveChatVO> = mutableListOf()
    private var mPatientId : String = ""
    private val VIEW_TYPE_SENDER = 1
    private val VIEW_TYPE_RECEIVER = 2
    private val VIEW_TYPE_MEDICINE = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<LiveChatVO> {
        return when(viewType){
            VIEW_TYPE_SENDER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message,parent,false)
                SenderMessageViewHolder(view)
            }
            VIEW_TYPE_RECEIVER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_other_message,parent,false)
                ReceiverMessageViewHolder(view)
            }else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_medicine_list,parent,false)
                ChatMedicineViewHolder(view,mDelegate)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<LiveChatVO>, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_SENDER){
            holder.bindData(mData[position])
        }else if (getItemViewType(position)  == VIEW_TYPE_RECEIVER){
            holder.bindData(mData[position])
        }else {
            holder.bindData(mData[position])
        }
    }

    override fun getItemCount(): Int {
        return mData.count()
    }

    override fun getItemViewType(position: Int): Int {
        return if (mData[position].sender_id == mPatientId && mData[position].medicineList?.count() == 0){
            VIEW_TYPE_SENDER
        }else if (mData[position].medicineList?.count() != 0){
            VIEW_TYPE_MEDICINE
        }else {
            VIEW_TYPE_RECEIVER
        }
    }

    fun addNewData(data : MutableList<LiveChatVO>){
        mData = data
        notifyDataSetChanged()
    }

    fun addPatientId(id : String){
        mPatientId = id
    }
}