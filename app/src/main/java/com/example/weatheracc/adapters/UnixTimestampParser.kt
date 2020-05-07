package com.example.weatheracc.adapters

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun parseToDayMonthYear(timestamp: Int): String {
    val value =
        SimpleDateFormat("dd MMMM, YYYY").format(Date((timestamp.toLong() - offset()) * 1000))
    return if (value[0] == '0')
        value.substring(1)
    else
        value
}

fun parseToDayMonthHourMinutes(timestamp: Int): String {
    val value =
        SimpleDateFormat("dd MMMM HH:mm").format(Date((timestamp.toLong() - offset()) * 1000))
    return if (value[0] == '0')
        value.substring(1)
    else
        value
}

fun parseToDayName(timestamp: Int): String =
    SimpleDateFormat("EEEE").format(Date((timestamp.toLong() - offset()) * 1000)).capitalize()


fun parseToHourMinutes(timestamp: Int): String =
    SimpleDateFormat("HH:mm").format(Date((timestamp.toLong() - offset()) * 1000))

private fun offset(): Long {
    val mCalendar = GregorianCalendar();
    val mTimeZone = mCalendar.timeZone;
    val mGMTOffset = mTimeZone.getOffset(Date().time)
    return TimeUnit.SECONDS.convert(mGMTOffset.toLong(), TimeUnit.MILLISECONDS)
}



