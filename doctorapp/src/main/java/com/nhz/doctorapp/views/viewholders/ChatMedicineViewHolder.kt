package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nhz.doctorapp.R
import com.nhz.shared.data.vos.LiveChatVO
import com.nhz.shared.data.vos.PrescriptionVO

class ChatMedicineViewHolder(itemview : View) : BaseViewHolder<LiveChatVO>(itemview) {

    private val listMedicineName : ListView = itemview.findViewById(R.id.listMedicineName)
    private val tvMessageDeliveredTime : TextView = itemview.findViewById(R.id.tvMedicineMessageTime)
    private val ivChatDoctorImage : ImageView = itemview.findViewById(R.id.ivChatDoctorImage)

    override fun bindData(data: LiveChatVO) {
        mData = data
        val adapter = ArrayAdapter(itemView.context,R.layout.item_medicine_name,data.medicineList!!)
        listMedicineName.adapter = adapter
        tvMessageDeliveredTime.text = data.time
        if (data.sender_image != ""){
            Glide.with(itemView.context)
                    .load(data.sender_image)
                    .into(ivChatDoctorImage)
        }
    }
}