package com.duybui.doapp.ui.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.duybui.doapp.data.model.Event
import com.duybui.doapp.data.model.EventStatus
import com.duybui.doapp.data.model.Location
import com.duybui.doapp.data.model.Notification
import com.duybui.doapp.ui.base.BaseViewModel

class HomeViewModel(application: Application) : BaseViewModel(application) {
    private val _eventList = MutableLiveData<List<Event>>()
    val events: MutableLiveData<List<Event>>
        get() = _eventList

    init {
        presentationComponent.inject(this)
        dummyData()
    }

    private fun dummyData() {
        val events = arrayListOf<Event>()
        for (i: Int in 1..20) {
            val event = Event("Brian’s Birthday", "Birthday", "", "", "", Location(), Notification(), EventStatus.OVERDUE)
            events.add(event)
        }
        _eventList.value = events
    }
}