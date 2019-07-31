package com.duybui.doapp.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory

object ImageHelper {
    fun decodeSampleBitmapFromResource(resources: Resources, resId: Int, reqWidth: Int, reqHeight: Int): Bitmap {
        //First decode with inJustDecodeBounds = true to check dimensions
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, resId, options)

        //calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
        //decode bitmap
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(resources, resId, options)
    }

    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        // Raw height and width of image
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2

            //Calculate the largest inSampleSize value that is a power of 2 and keeps both
            //height and width larger than the requested height and wodth
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}
