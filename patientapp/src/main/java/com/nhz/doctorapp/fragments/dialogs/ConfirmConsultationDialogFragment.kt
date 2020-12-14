package com.nhz.doctorapp.fragments.dialogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.nhz.doctorapp.R
import com.nhz.doctorapp.activities.CaseSummaryFormActivity

class ConfirmConsultationDialogFragment : DialogFragment() {

    private lateinit var tvConfirmConsultation : TextView
    private lateinit var tvCancel : TextView
    private lateinit var tvConfirm : TextView
    private var specialityId : Int = 0
    private var specialityType : String = ""
    private var oldOrNew : Boolean = false

    companion object{
        const val TAB_CONFIRM_CONSULTATION = "TAG_CONFIRM_CONSULTATION"
        const val BUNDLE_SPECIALITY_TYPE = "BUNDLE_SPECIALITY_TYPE"
        const val BUNDLE_SPECIALITY_ID = "BUNDLE_SPECIALITY_ID"
        const val BUNDLE_OLD_OR_NEW = "BUNDLE_OLD_OR_NEW"

        fun newFragment(specialityType : String,specialityId : Int,oldOrNew : Boolean) : ConfirmConsultationDialogFragment{
            val bundle = Bundle()
            bundle.putString(BUNDLE_SPECIALITY_TYPE,specialityType)
            bundle.putInt(BUNDLE_SPECIALITY_ID,specialityId)
            bundle.putBoolean(BUNDLE_OLD_OR_NEW,oldOrNew)
            return ConfirmConsultationDialogFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirm_consultation_dialog, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            specialityType = it.getString(BUNDLE_SPECIALITY_TYPE,"")
            specialityId = it.getInt(BUNDLE_SPECIALITY_ID,0)
            oldOrNew = it.getBoolean(BUNDLE_OLD_OR_NEW,false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvConfirmConsultation = view.findViewById(R.id.tvConfirmConsultation)
        tvCancel = view.findViewById(R.id.tvNotConsult)
        tvConfirm = view.findViewById(R.id.tvConfirmConsult)

        tvConfirmConsultation.text = "$specialityType ဆရာဝန်နှင့် တိုင်ပင်ဆွေးနွေးရန် Consultation Request ပြုလုပ်မှာသေချာပါသလား?"

        setOnClickListeners(specialityId,oldOrNew)

    }

    private fun setOnClickListeners(id : Int,oldOrNew : Boolean){
        activity?.let {activity ->
            tvCancel.setOnClickListener {
                dismiss()
            }
            tvConfirm.setOnClickListener {
                startActivity(CaseSummaryFormActivity.newIntent(id,oldOrNew,activity))
                dismiss()
            }
        }
    }
}