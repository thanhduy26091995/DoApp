package com.duybui.basemvvmkotlin.di.application

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Singleton
    @Provides
    internal fun provideApplication(): Application {
        return this.application
    }

    @Singleton
    @Provides
    internal fun provideContext(): Context {
        return this.application
    }
}
