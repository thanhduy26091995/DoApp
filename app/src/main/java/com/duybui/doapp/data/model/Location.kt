package com.duybui.doapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location (val lat: Double, val lng: Double, val name: String): Parcelable{
    constructor(): this(0.0, 0.0 ,"")
}