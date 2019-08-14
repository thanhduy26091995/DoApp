package com.duybui.doapp.ui.calendar

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.duybui.doapp.R
import com.duybui.doapp.ui.base.BaseActivity
import com.duybui.doapp.ui.base.BaseFragment
import com.duybui.doapp.utils.EventDecorator
import com.duybui.doapp.utils.TimeHelper
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.android.synthetic.main.fragment_calendar.*

class CalendarFragment : BaseFragment(), BaseActivity.ToolbarClickListener {

    private var calendarDates = arrayListOf<CalendarDay>()

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
        }

        calendarView.setOnMonthChangedListener { materialCalendarView, calendarDay ->
            mBaseActivity?.updateCurrentDay(TimeHelper.formatTime(calendarDay.date, TimeHelper.PATTERN_MONTH_YEAR))
        }
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
}