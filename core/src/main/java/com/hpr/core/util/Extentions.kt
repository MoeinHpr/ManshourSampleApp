package com.hpr.core.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat

fun Context.getBitmapFromImage(drawable: Int): Bitmap {
    val db = ContextCompat.getDrawable(this, drawable)
    val bit = Bitmap.createBitmap(
        db!!.intrinsicWidth, db.intrinsicHeight, Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bit)
    db.setBounds(0, 0, canvas.width, canvas.height)
    db.draw(canvas)
    return bit
}