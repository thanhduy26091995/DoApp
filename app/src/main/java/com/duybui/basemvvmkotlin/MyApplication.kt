package com.duybui.basemvvmkotlin

import android.app.Application
import com.duybui.basemvvmkotlin.di.application.ApiModule
import com.duybui.basemvvmkotlin.di.application.ApplicationComponent
import com.duybui.basemvvmkotlin.di.application.ApplicationModule
import com.duybui.basemvvmkotlin.di.application.DaggerApplicationComponent
import uk.co.chrisjenx.calligraphy.CalligraphyConfig


class MyApplication : Application() {

    var applicationComponent: ApplicationComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        //init Dagger 2
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .apiModule(ApiModule())
            .build()
        applicationComponent?.let {
            it.inject(this)
        }
        //init custom font
        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/IBMPlexSans-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
}
