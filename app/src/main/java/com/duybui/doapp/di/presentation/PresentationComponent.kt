package com.duybui.doapp.di.presentation

import com.duybui.doapp.ui.MainActivity
import com.duybui.doapp.ui.calendar.CalendarFragment
import com.duybui.doapp.ui.home.HomeFragment
import com.duybui.doapp.ui.home.HomeViewModel
import com.duybui.doapp.ui.lists.ListsFragment
import com.duybui.doapp.ui.users.UserViewModel
import dagger.Subcomponent

@Subcomponent(modules = [PresentationModule::class, ViewModelModule::class])
interface PresentationComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(userViewModel: UserViewModel)

    fun inject(homeViewModel: HomeViewModel)

    fun inject(homeFragment: HomeFragment)

    fun inject(calendarFragment: CalendarFragment)

    fun inject(listFragment: ListsFragment)

}
