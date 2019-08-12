package com.duybui.doapp.data.model

data class Event(val title: String, val description: String, val date: String, val from: String, val to: String,
                 val location: Location, val notification: Notification)