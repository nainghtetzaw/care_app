package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.nhz.doctorapp.R
import com.nhz.doctorapp.delegates.ChatMedicineDelegate
import com.nhz.shared.data.vos.LiveChatVO

class ChatMedicineViewHolder(itemview : View,val mDelegate : ChatMedicineDelegate) : BaseViewHolder<LiveChatVO>(itemview) {

    private val ivDoctorImage : ImageView = itemview.findViewById(R.id.ivDoctorImage)
    private val listMedicineName : ListView = itemview.findViewById(R.id.listMedicineName)
    private val btnOrderMedicine : Button = itemview.findViewById(R.id.btnOrderMedicine)
    private val tvMedicineMessageTime : TextView = itemview.findViewById(R.id.tvMedicineMessageTime)

    init {
        btnOrderMedicine.setOnClickListener {
            mData?.let {
                mDelegate.onTapOrderMedicine()
            }
        }
    }

    override fun bindData(data: LiveChatVO) {
        mData = data

        if (data.receiver_image != ""){
            Glide.with(itemView.context)
                    .load(data.receiver_image)
                    .into(ivDoctorImage)
        }
        val adapter = ArrayAdapter(itemView.context,R.layout.item_medicine_name,data.medicineList!!)
        listMedicineName.adapter = adapter
        tvMedicineMessageTime.text = data.time
    }
}