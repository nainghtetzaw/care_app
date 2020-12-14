package com.nhz.doctorapp.views.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.withStyledAttributes
import com.nhz.doctorapp.R

class CustomEditText(context: Context,attrs : AttributeSet) : AppCompatEditText(context,attrs) {

    private lateinit var mPrefix : String
    private var mPrefixColor  = Color.BLACK
    private val mPrefixRect = Rect()

    init {
        context.withStyledAttributes(attrs, R.styleable.CustomEditText){
            mPrefix = getString(R.styleable.CustomEditText_prefixText) ?: mPrefix
            mPrefixColor = getColor(R.styleable.CustomEditText_prefixTextColor,mPrefixColor)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        paint.getTextBounds(mPrefix,mPrefix.length,0,mPrefixRect)

        mPrefixRect.right += paint.measureText(" ").toInt()

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {

        paint.color = mPrefixColor
        canvas?.drawText(mPrefix,super.getCompoundPaddingLeft().toFloat(),baseline.toFloat(),paint)

        super.onDraw(canvas)

    }

    override fun getCompoundPaddingLeft(): Int {
        return super.getCompoundPaddingLeft() + mPrefixRect.width()
    }

}