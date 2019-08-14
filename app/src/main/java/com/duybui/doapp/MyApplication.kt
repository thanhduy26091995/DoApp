package com.duybui.doapp

import android.app.Application
import com.duybui.doapp.di.application.ApiModule
import com.duybui.doapp.di.application.ApplicationComponent
import com.duybui.doapp.di.application.ApplicationModule
import com.duybui.doapp.di.application.DaggerApplicationComponent
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
        applicationComponent?.inject(this)
        //init custom font
        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/AvenirLTStd-Roman.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
}
