package com.nhz.doctorapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nhz.doctorapp.R

class SetConsultationTimeFragmentDialog : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SetConsultationTimeFragmentDialog().apply {
                    arguments = Bundle()
                }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_set_consultation_time_dialog, container, false)
    }

}