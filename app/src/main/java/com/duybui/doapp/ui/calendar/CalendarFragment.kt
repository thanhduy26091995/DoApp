package com.duybui.doapp.ui.calendar

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.duybui.doapp.R
import com.duybui.doapp.ui.base.BaseActivity
import com.duybui.doapp.ui.base.BaseFragment
import com.duybui.doapp.ui.base.ViewModelFactory
import com.duybui.doapp.ui.home.HomeAdapter
import com.duybui.doapp.ui.home.HomeViewModel
import com.duybui.doapp.utils.EventDecorator
import com.duybui.doapp.utils.TimeHelper
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.android.synthetic.main.fragment_calendar.*
import javax.inject.Inject

class CalendarFragment : BaseFragment(), BaseActivity.ToolbarClickListener {

    private var calendarDates = arrayListOf<CalendarDay>()


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var homeAdapter: HomeAdapter

    private val homeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //listen event from Left, Right icon click
        mBaseActivity?.toolbarClickListener = this
        //hide top bar view
        calendarView.topbarVisible = false
        //update toolbar
        updateToolbarTitle()
        calendarView.setOnDateChangedListener { materialCalendarView, calendarDay, b ->
            calendarDates.add(calendarDay)
            calendarView.addDecorator(EventDecorator(context, Color.RED, calendarDates))
            //change mode
        }

        calendarView.setOnMonthChangedListener { materialCalendarView, calendarDay ->
            mBaseActivity?.updateCurrentDay(TimeHelper.formatTime(calendarDay.date, TimeHelper.PATTERN_MONTH_YEAR))
        }

        presentationComponent.inject(this)
        setupRecyclerView()

        homeViewModel.events.observe(this, Observer { value ->
            homeAdapter.setEventList(value)
        })
    }

    override fun onNextMonthClick() {
        calendarView.goToNext()
        updateToolbarTitle()
    }

    override fun onPreviousMonthClick() {
        calendarView.goToPrevious()
        updateToolbarTitle()
    }

    private fun updateToolbarTitle() {
        mBaseActivity?.updateCurrentDay(TimeHelper.formatTime(calendarView.currentDate.date, TimeHelper.PATTERN_MONTH_YEAR))
    }

    private fun setupRecyclerView() {
        rvEvents.layoutManager = LinearLayoutManager(activity)
        rvEvents.adapter = homeAdapter
    }
}