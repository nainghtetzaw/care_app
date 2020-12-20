package com.nhz.doctorapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nhz.doctorapp.R
import com.nhz.doctorapp.adapters.OrderMedicineAdapter
import com.nhz.doctorapp.fragments.dialogs.CheckOutDialogFragment
import com.nhz.doctorapp.mvp.presenters.OrderMedicinePresenter
import com.nhz.doctorapp.mvp.presenters.impls.OrderMedicinePresenterImpl
import com.nhz.doctorapp.mvp.views.OrderMedicineView
import com.nhz.shared.data.vos.PrescriptionVO

class OrderMedicinesActivity : AppCompatActivity(),OrderMedicineView {

    private lateinit var ivOrderMedicineBack : ImageView
    private lateinit var rViewOrderMedicineList : RecyclerView
    private lateinit var tvOrderTotalAmount : TextView
    private lateinit var etOrderState : EditText
    private lateinit var etTownShip : EditText
    private lateinit var etInputAddress : EditText
    private lateinit var btnOrder : Button

    private lateinit var mAdapter : OrderMedicineAdapter
    private lateinit var mLayoutManager : LinearLayoutManager
    private lateinit var mPresenter : OrderMedicinePresenter
    private lateinit var checkoutDialogFragment : CheckOutDialogFragment

    companion object{

        const val CONSULTATION_ID = "CONSULTATION_ID"

        fun newIntent(consultationId : String,context: Context) : Intent{
            return Intent(context,OrderMedicinesActivity::class.java)
                .putExtra(CONSULTATION_ID,consultationId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_medicines)

        ivOrderMedicineBack = findViewById(R.id.ivOrderMedicineBack)
        rViewOrderMedicineList = findViewById(R.id.rViewOrderMedicineList)
        tvOrderTotalAmount = findViewById(R.id.tvOrderTotalAmount)
        etOrderState = findViewById(R.id.etOrderState)
        etTownShip = findViewById(R.id.etTownship)
        etInputAddress = findViewById(R.id.etInputAddress)
        btnOrder = findViewById(R.id.btnOrder)
        setUpPresenter()
        setUpRecyclerView()

        val consultationId = intent.getStringExtra(CONSULTATION_ID)
        consultationId?.let { mPresenter.onUiReady(it) }
        btnOrder.setOnClickListener {
            if (etInputAddress.text.toString() != "" || etOrderState.text.toString() != "" || etTownShip.text.toString() != ""){
                mPresenter.addCheckOut("${etInputAddress.text},${etTownShip.text},${etOrderState.text}")
                showCheckOutDialogFragment(consultationId!!)
            }else {
                Toast.makeText(this,"Please input your address",Toast.LENGTH_LONG).show()
            }
        }

        ivOrderMedicineBack.setOnClickListener {
            finish()
        }

    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(OrderMedicinePresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun showCheckOutDialogFragment(consultationId: String){
        checkoutDialogFragment = CheckOutDialogFragment.newInstance(consultationId)
        checkoutDialogFragment.show(supportFragmentManager,CheckOutDialogFragment.TAG_CHECK_OUT)
    }

    private fun setUpRecyclerView(){
        mAdapter = OrderMedicineAdapter()
        mLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        rViewOrderMedicineList.adapter= mAdapter
        rViewOrderMedicineList.layoutManager = mLayoutManager
    }

    override fun showMedicineList(data: List<PrescriptionVO>) {
        mAdapter.addNewData(data.toMutableList())
    }

    override fun showTotalPrice(price: Int) {
        tvOrderTotalAmount.text = price.toString()
    }
}