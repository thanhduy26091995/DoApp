package com.duybui.basemvvmkotlin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserSchema::class], version = 1)
abstract class MVVMDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
}