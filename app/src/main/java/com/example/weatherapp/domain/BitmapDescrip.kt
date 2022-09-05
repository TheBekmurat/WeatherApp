package com.example.weatherapp.domain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

interface BitmapDescriptor{

    fun bitmapDescriptor(res: Int): BitmapDescriptor?

     class BitmapDescriptorFromVector(private val context: Context ): com.example.weatherapp.domain.BitmapDescriptor{

         override fun bitmapDescriptor(res: Int): BitmapDescriptor? {
             return ContextCompat.getDrawable(context, res)?.run {
                 setBounds(0, 0, intrinsicWidth, intrinsicHeight)
                 val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
                 draw(Canvas(bitmap))
                 BitmapDescriptorFactory.fromBitmap(bitmap)
             }
         }
     }
}