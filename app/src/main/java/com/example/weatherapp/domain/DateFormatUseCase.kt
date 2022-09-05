package com.example.weatherapp.domain

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
//Parser
const val YYYY_MMM_DD_HH_MM = "yyyy-MM-dd HH:mm"
const val YYYY_MM_DD = "yyyy-MM-dd"
//Formatter
const val HH_MM = "HH:mm"
const val dd_MMM_YYYY_HH_MM = "dd MMM yyyy/HH:mm"
const val MMM_DD = "MMM,dd"

interface DateFormatUseCase {

    fun execute(date: String,formatter:String,parser: String): String

    class DateFormatUseCase: com.example.weatherapp.domain.DateFormatUseCase{

        @SuppressLint("SimpleDateFormat")
        override fun execute(date: String, formatter:String, parser: String): String {
            val formatter = SimpleDateFormat(formatter)
            val parser = SimpleDateFormat(parser)
            return formatter.format(parser.parse(date))
        }
    }
}