package com.duybui.basemvvmkotlin.data.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDAO {
    @Query("Select * from UserSchema")
    fun getUserList(): List<UserSchema>
}