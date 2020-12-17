package com.nhz.doctorapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nhz.doctorapp.R
import com.nhz.doctorapp.adapters.PrescriptionMedicineAdapter
import com.nhz.doctorapp.fragments.PrescribeMedicineFragmentDialog
import com.nhz.doctorapp.mvp.presenters.impls.PrescriptionMedicinesPresenterImpl
import com.nhz.doctorapp.mvp.presenters.interfaces.PrescriptionMedicinesPresenter
import com.nhz.doctorapp.mvp.views.PrescriptionMedicinesView
import com.nhz.shared.data.vos.MedicinesVO
import io.reactivex.rxjava3.core.Observable
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class MedicineListActivity : AppCompatActivity(),PrescriptionMedicinesView {

    private lateinit var atvSearchMedicine : AutoCompleteTextView
    private lateinit var rViewPrescriptionMedicine : RecyclerView
    private lateinit var btnFinishConsultation : Button
    private lateinit var ivMedicineListBack : ImageView

    private lateinit var mLayoutManager : LinearLayoutManager
    private lateinit var mAdapter : PrescriptionMedicineAdapter
    private lateinit var mPresenter : PrescriptionMedicinesPresenter
    private lateinit var mPrescribeDialog : PrescribeMedicineFragmentDialog

    companion object{

        const val SPECIALITY_ID = "SPECIALITY_ID"
        const val CONSULTATION_ID = "CONSULTATION_ID"

        fun newIntent(specialityId : Int,consultationId : String,context: Context) : Intent{
            return Intent(context,MedicineListActivity::class.java)
                    .putExtra(SPECIALITY_ID,specialityId)
                    .putExtra(CONSULTATION_ID,consultationId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine_list)

        atvSearchMedicine = findViewById(R.id.atvSearchMedicine)
        rViewPrescriptionMedicine = findViewById(R.id.rViewPrescriptionMedicine)
        btnFinishConsultation = findViewById(R.id.btnFinishConsultation)
        ivMedicineListBack = findViewById(R.id.ivMedicineListBack)
        setUpPresenter()
        setUpRecyclerView()

        val specialityId = intent.getIntExtra(SPECIALITY_ID,0)
        val consultationId = intent.getStringExtra(CONSULTATION_ID)
        mPresenter.onUiReady(specialityId,consultationId!!,this,this)

        btnFinishConsultation.setOnClickListener {
            mPresenter.sendMessagePrescription()
            mPresenter.finishConsultation()
            startActivity(HomeActivity.newIntent(this))
            finish()
        }

        ivMedicineListBack.setOnClickListener {
            finish()
        }

        atvSearchMedicine.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filteredList = mutableListOf<MedicinesVO>()
                val dataList = mAdapter.getData()
                if (s.toString() != ""){
                    dataList.forEach {
                        if (it.name.toLowerCase().contains(s.toString().toLowerCase())){
                            filteredList.add(it)
                        }
                    }
                    mAdapter.addNewData(filteredList)
                }else {
                    mPresenter.addOldDataToMedicineList()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    override fun showMedicineList(data: List<MedicinesVO>) {
        mAdapter.addNewData(data.toMutableList())
    }

    override fun showPrescribeMedicineFragment(name: String,consultationId : String,medicinePrice : Int) {
        mPrescribeDialog = PrescribeMedicineFragmentDialog.newInstance(name,consultationId,medicinePrice)
        mPrescribeDialog.show(supportFragmentManager,PrescribeMedicineFragmentDialog.TAG_PRESCRIBE_MEDICINE)
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(PrescriptionMedicinesPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpRecyclerView(){
        mLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mAdapter = PrescriptionMedicineAdapter(mPresenter)
        rViewPrescriptionMedicine.layoutManager = mLayoutManager
        rViewPrescriptionMedicine.adapter = mAdapter
    }

    private fun getTimeStamp() : String {
        val date = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("hh:mm a")
        return date.format(formatter)
    }

}