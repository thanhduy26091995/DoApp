package com.duybui.doapp.di.application

import android.content.Context
import androidx.room.Room
import com.duybui.doapp.data.local.MVVMDatabase
import com.duybui.doapp.data.local.UserDAO
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class LocalDatabaseModule {
    @Singleton
    @Provides
    internal fun provideDatabase(context: Context): MVVMDatabase {
        return Room.databaseBuilder(context, MVVMDatabase::class.java, MVVMDatabase::class.java.simpleName).build()
    }

    @Singleton
    @Provides
    internal fun provideUserDao(mvvmDatabase: MVVMDatabase): UserDAO {
        return mvvmDatabase.userDAO()
    }
}
