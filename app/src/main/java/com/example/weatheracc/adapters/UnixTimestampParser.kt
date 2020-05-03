package com.example.weatheracc.adapters

import java.text.SimpleDateFormat
import java.util.*

fun parseToDayMonthYear(timestamp: Int): String =
    SimpleDateFormat("dd MMMM, YYYY").format(Date(timestamp.toLong() * 1000))