package com.duybui.basemvvmkotlin.data.response

import com.google.gson.annotations.SerializedName

data class BaseResponseObject<T>(
    @SerializedName("message")
    val message: String,

    @SerializedName("code")
    val code: Int,

    @SerializedName("results")
    val data: T
)