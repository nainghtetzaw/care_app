package com.nhz.doctorapp

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.graphics.scale
import com.bumptech.glide.Glide

fun Bitmap.scaleToRatio(scale : Double) :Bitmap{
    return this.scale((this.width * scale).toInt(),(this.height * scale).toInt())
}

fun Uri.toBitmap(context: Context) : Bitmap{
    return Glide.with(context)
        .asBitmap()
        .load(this)
        .submit()
        .get()
}