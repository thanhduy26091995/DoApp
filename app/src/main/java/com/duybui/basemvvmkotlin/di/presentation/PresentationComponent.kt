package com.duybui.basemvvmkotlin.di.presentation

import com.duybui.basemvvmkotlin.MainActivity
import com.duybui.basemvvmkotlin.ui.users.UserViewModel
import dagger.Subcomponent

@Subcomponent(modules = [PresentationModule::class, ViewModelModule::class])
interface PresentationComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(userViewModel: UserViewModel)

}
