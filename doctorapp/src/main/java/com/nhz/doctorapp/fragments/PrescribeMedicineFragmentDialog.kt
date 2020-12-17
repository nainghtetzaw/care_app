package com.nhz.doctorapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.nhz.doctorapp.R
import com.nhz.doctorapp.mvp.presenters.impls.PrescribePresenterImpl
import com.nhz.doctorapp.mvp.presenters.interfaces.PrescribePresenter
import com.nhz.doctorapp.mvp.views.PrescribeView
import com.nhz.shared.data.vos.PrescriptionVO

class PrescribeMedicineFragmentDialog : DialogFragment(),PrescribeView {

    private var medicineName : String = ""
    private var consultationId : String = ""
    private var medicinePrice : Int = 0
    private var prescription = PrescriptionVO()
    private var isMorningClicked : Boolean = false
    private var isNoonClicked : Boolean = false
    private var isNightClicked : Boolean = false
    private var isBeforeClicked : Boolean = false

    private lateinit var mPresenter : PrescribePresenter

    private lateinit var etInputQuantity : EditText
    private lateinit var etInputTotalDay : EditText
    private lateinit var spinnerTotalDay : Spinner
    private lateinit var btnMorning : Button
    private lateinit var btnNoon : Button
    private lateinit var btnNight : Button
    private lateinit var btnBeforeEat : Button
    private lateinit var btnAfterEat : Button
    private lateinit var etInputNote : EditText
    private lateinit var btnAdd : Button

    companion object{

        const val BUNDLE_MEDICINE_NAME = "BUNDLE_MEDICINE_NAME"
        const val BUNDLE_CONSULTATION_ID = "BUNDLE_CONSULTATION_ID"
        const val TAG_PRESCRIBE_MEDICINE = "TAB_PRESCRIBE_MEDICINE"
        const val BUNDLE_MEDICINE_PRICE = "BUNDLE_MEDICINE_PRICE"

        fun newInstance(medicineName : String,consultationId : String,medicinePrice : Int) : PrescribeMedicineFragmentDialog{
            return PrescribeMedicineFragmentDialog().apply {
                val bundle = Bundle()
                bundle.putString(BUNDLE_MEDICINE_NAME,medicineName)
                bundle.putString(BUNDLE_CONSULTATION_ID,consultationId)
                bundle.putInt(BUNDLE_MEDICINE_PRICE,medicinePrice)
                arguments = bundle
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val param = dialog?.window?.attributes?.apply {
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height = ViewGroup.LayoutParams.WRAP_CONTENT
        }
        dialog?.window?.attributes = param
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_prescribe_medicine_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etInputQuantity = view.findViewById(R.id.etInputQuantity)
        etInputTotalDay = view.findViewById(R.id.etInputTotalDay)
        spinnerTotalDay = view.findViewById(R.id.spinnerTotalDay)
        btnMorning = view.findViewById(R.id.btnMorning)
        btnNoon = view.findViewById(R.id.btnNoon)
        btnNight = view.findViewById(R.id.btnNight)
        btnBeforeEat = view.findViewById(R.id.btnBeforeEat)
        btnAfterEat = view.findViewById(R.id.btnAfterEat)
        etInputNote = view.findViewById(R.id.etInputNote)
        btnAdd = view.findViewById(R.id.btnAdd)
        setUpPresenter()
        setOnClickListeners()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            medicineName = it.getString(BUNDLE_MEDICINE_NAME,"")
            consultationId = it.getString(BUNDLE_CONSULTATION_ID,"")
            medicinePrice = it.getInt(BUNDLE_MEDICINE_PRICE,0)
        }
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(PrescribePresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setOnClickListeners(){

        activity?.let {activity ->
            btnMorning.setOnClickListener {
                if (!isMorningClicked){
                    btnMorning.background = ContextCompat.getDrawable(activity,R.drawable.rounded_corners_blue_button)
                    isMorningClicked = true
                }else {
                    btnMorning.background = ContextCompat.getDrawable(activity,R.drawable.rounded_corner_grey_white_stroke)
                    isMorningClicked = false
                }
            }

            btnNoon.setOnClickListener {
                if (!isNoonClicked){
                    btnNoon.background = ContextCompat.getDrawable(activity,R.drawable.rounded_corners_blue_button)
                    isNoonClicked = true
                }else {
                    btnNoon.background = ContextCompat.getDrawable(activity,R.drawable.rounded_corner_grey_white_stroke)
                    isNoonClicked = false
                }
            }

            btnNight.setOnClickListener {
                if (!isNightClicked){
                    btnNight.background = ContextCompat.getDrawable(activity,R.drawable.rounded_corners_blue_button)
                    isNightClicked = true
                }else {
                    btnNight.background = ContextCompat.getDrawable(activity,R.drawable.rounded_corner_grey_white_stroke)
                    isNightClicked = false
                }
            }

            btnBeforeEat.setOnClickListener {
                if (!isBeforeClicked){
                    btnBeforeEat.background = ContextCompat.getDrawable(activity,R.drawable.rounded_corners_blue_button)
                    btnAfterEat.background = ContextCompat.getDrawable(activity,R.drawable.rounded_corner_grey_white_stroke)
                    isBeforeClicked = true
                }else {
                    btnBeforeEat.background = ContextCompat.getDrawable(activity,R.drawable.rounded_corner_grey_white_stroke)
                    isBeforeClicked = false
                }
            }

            btnAfterEat.setOnClickListener {
                if (!isBeforeClicked){
                    btnAfterEat.background = ContextCompat.getDrawable(activity,R.drawable.rounded_corners_blue_button)
                    isBeforeClicked= true
                }else {
                    btnAfterEat.background = ContextCompat.getDrawable(activity,R.drawable.rounded_corners_blue_button)
                    btnBeforeEat.background = ContextCompat.getDrawable(activity,R.drawable.rounded_corner_grey_white_stroke)
                    isBeforeClicked = false
                }
            }

            btnAdd.setOnClickListener {
                if (etInputQuantity.text.toString() != "" || etInputTotalDay.text.toString() != ""){
                    prescription = PrescriptionVO(
                            medicine = medicineName,
                            quantity = etInputQuantity.text.toString().toInt(),
                            price = medicinePrice,
                            day = "${etInputTotalDay.text.toString().toInt()} ${spinnerTotalDay.selectedItem}",
                            morning = isMorningClicked,
                            evening = isNoonClicked,
                            night = isNightClicked,
                            note = etInputNote.text.toString(),
                            beforeOrAfter = isBeforeClicked
                    )
                    mPresenter.onClickAdd(consultationId,prescription)
                    dismiss()
                }
            }
        }

    }

}