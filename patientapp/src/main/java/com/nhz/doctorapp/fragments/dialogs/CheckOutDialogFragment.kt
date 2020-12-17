package com.nhz.doctorapp.fragments.dialogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nhz.doctorapp.R
import com.nhz.doctorapp.activities.MainActivity
import com.nhz.doctorapp.adapters.CheckOutMedicineListAdapter
import com.nhz.doctorapp.mvp.presenters.CheckOutPresenter
import com.nhz.doctorapp.mvp.presenters.impls.CheckOutPresenterImpl
import com.nhz.doctorapp.mvp.views.CheckOutView
import com.nhz.shared.data.vos.PrescriptionVO


class CheckOutDialogFragment : DialogFragment(),CheckOutView {

    private lateinit var rViewCheckOutMedicineList : RecyclerView
    private lateinit var tvCheckOutSubTotal : TextView
    private lateinit var tvCheckOutTotal : TextView
    private lateinit var tvCheckOutAddress : TextView
    private lateinit var btnCheckOut : Button

    private lateinit var mAdapter : CheckOutMedicineListAdapter
    private lateinit var mLayoutManager : LinearLayoutManager
    private lateinit var mPresenter : CheckOutPresenter

    companion object{

        const val TAG_CHECK_OUT = "TAG_CHECK_OUT"

        fun newInstance() : CheckOutDialogFragment{
            return  CheckOutDialogFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_check_out_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rViewCheckOutMedicineList = view.findViewById(R.id.rViewCheckOutMedicineList)
        tvCheckOutSubTotal = view.findViewById(R.id.tvCheckOutSubTotal)
        tvCheckOutTotal = view.findViewById(R.id.tvCheckOutTotal)
        tvCheckOutAddress = view.findViewById(R.id.tvCheckOutAddress)
        btnCheckOut = view.findViewById(R.id.btnCheckOut)
        setUpPresenter()
        setUpRecyclerView()

        mPresenter.onUiReady()

        btnCheckOut.setOnClickListener {
            startActivity(MainActivity.newIntent(view.context))
            dismiss()
        }

    }

    override fun onResume() {
        super.onResume()
        val param = dialog?.window?.attributes?.apply {
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height = ViewGroup.LayoutParams.MATCH_PARENT
        }
        dialog?.window?.attributes = param
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(CheckOutPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpRecyclerView(){
        activity?.let {
            mAdapter = CheckOutMedicineListAdapter()
            mLayoutManager = LinearLayoutManager(it,LinearLayoutManager.VERTICAL,false)

            rViewCheckOutMedicineList.adapter = mAdapter
            rViewCheckOutMedicineList.layoutManager = mLayoutManager

        }
    }

    override fun showPrescriptionData(data: List<PrescriptionVO>) {
        mAdapter.addNewData(data.toMutableList())
    }

    override fun showAddressAndPrice(address: String, total: String, subtotal: String) {
        tvCheckOutSubTotal.text = subtotal
        tvCheckOutAddress.text = address
        tvCheckOutTotal.text = total
    }

}