package com.nhz.doctorapp.delegates

interface MedicineListDelegate {
    fun onAddMedicine(name : String,price : Int)
    fun onDeleteMedicine(name : String)
}