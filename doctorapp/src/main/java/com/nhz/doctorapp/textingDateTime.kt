package com.nhz.doctorapp

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun getTimeStamp() : String{
    val date = LocalTime.now()
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    val text = date.format(formatter)
    return text
}

fun main(){
    print("Time = ${getTimeStamp()}")
}