package com.duybui.doapp.di.application


import android.app.Application
import com.duybui.doapp.di.presentation.PresentationComponent
import com.duybui.doapp.di.presentation.PresentationModule
import com.duybui.doapp.di.service.ServiceComponent
import com.duybui.doapp.di.service.ServiceModule
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ApiModule::class])
interface ApplicationComponent {
    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent

    fun newServiceComponent(serviceModule: ServiceModule): ServiceComponent

    fun inject(application: Application)
}
