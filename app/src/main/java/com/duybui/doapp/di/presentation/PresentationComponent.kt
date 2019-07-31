package com.duybui.doapp.di.presentation

import com.duybui.doapp.MainActivity
import com.duybui.doapp.ui.users.UserViewModel
import dagger.Subcomponent

@Subcomponent(modules = [PresentationModule::class, ViewModelModule::class])
interface PresentationComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(userViewModel: UserViewModel)

}
