package com.duybui.basemvvmkotlin.data.model

import com.google.gson.annotations.SerializedName

data class Picture(
    @SerializedName("large")
    val large: String
)