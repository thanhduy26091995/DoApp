package com.duybui.doapp.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

object UIUtils {
    fun getDp(context: Context, px: Int): Int {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, px.toFloat(), context.resources.displayMetrics).toInt()
    }

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}