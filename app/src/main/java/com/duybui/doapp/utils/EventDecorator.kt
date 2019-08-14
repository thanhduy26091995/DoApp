package com.duybui.doapp.utils

import android.content.Context
import com.duybui.doapp.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class EventDecorator(val context: Context?, val color: Int, private val dates: List<CalendarDay>) : DayViewDecorator {
    override fun shouldDecorate(p0: CalendarDay?): Boolean {
        return dates.contains(p0)
    }

    override fun decorate(p0: DayViewFacade?) {

        p0?.run {
            addSpan(DotSpan(UIUtils.dpToPx(2).toFloat(), R.color.colorWhiteOpacity50))
        }
    }
}