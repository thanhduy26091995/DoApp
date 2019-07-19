package com.duybui.basemvvmkotlin.data.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserSchema(
    @PrimaryKey
    @NonNull
    val id: String,

    @ColumnInfo(name = "name")
    val name: String
)