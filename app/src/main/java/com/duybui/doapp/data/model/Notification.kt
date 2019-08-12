package com.duybui.doapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Notification (val notificationTime: String, val type: NotificationType): Parcelable{
    constructor(): this("", NotificationType.EMAIL)
}