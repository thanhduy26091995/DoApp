package com.duybui.doapp.utils

import com.prolificinteractive.materialcalendarview.CalendarDay
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.TemporalAccessor
import java.text.SimpleDateFormat

object TimeHelper {
    const val PATTERN_MONTH_YEAR = "MMM yyyy"

    fun formatTime(date: TemporalAccessor, pattern: String): String {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return formatter.format(date)
    }

    fun formatTime(date: CalendarDay, pattern: String): String {
        val simpleDateFormat =  SimpleDateFormat(pattern)
        return simpleDateFormat.format(date)
    }
}