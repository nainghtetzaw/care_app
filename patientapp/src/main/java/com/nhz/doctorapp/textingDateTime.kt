package com.nhz.doctorapp

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun getTimeStamp(dateStr : String) : Date? {
    try {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date : Date = sdf.parse(dateStr)
        return date
    }catch (err : ParseException){
        return null
    }
}

fun getCurrentDate() : String{
    val date = Date()
    val formatter = SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
    val text = formatter.format(date)
    return text
}

fun getCurrentTime() : String{
    val date = Date()
    val formatter = SimpleDateFormat("hh:mm a")
    return formatter.format(date)
}

fun main(){
    println(getCurrentTime())
//    println(getTimeStamp("2020-12-17T00:33:37Z"))
}