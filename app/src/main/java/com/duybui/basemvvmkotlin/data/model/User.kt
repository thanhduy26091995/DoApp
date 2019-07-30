package com.duybui.basemvvmkotlin.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class User(
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    var phone: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("picture")
    val picture: Picture
): Parcelable