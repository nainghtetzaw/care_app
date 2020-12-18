package com.nhz.doctorapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.nhz.doctorapp.R

class SetConsultationTimeFragmentDialog : DialogFragment() {

    private lateinit var btnChoosePostponeTime : Button

    companion object {

        const val TAG_SELECT_TIME = "TAG_SELECT_TIME"

        @JvmStatic
        fun newInstance() =
                SetConsultationTimeFragmentDialog().apply {
                    arguments = Bundle()
                }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_set_consultation_time_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnChoosePostponeTime = view.findViewById(R.id.btnChoosePostponeTime)

        btnChoosePostponeTime.setOnClickListener {
            dismiss()
        }
    }

}