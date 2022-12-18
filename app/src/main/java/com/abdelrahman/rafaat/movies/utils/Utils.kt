package com.abdelrahman.rafaat.movies.utils

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import com.abdelrahman.rafaat.movies.R
import java.util.*
import kotlin.collections.ArrayList

fun connectInternet(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        context.startActivity(Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY))
    } else {
        context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
    }
}

fun getListOfYears(context: Context): List<String> {
    val years = ArrayList<String>()
    var thisYear = Calendar.getInstance().get(Calendar.YEAR)
    years.add(context.getString(R.string.allTimes))
    repeat(33) {
        years.add(thisYear.toString())
        thisYear--
    }

    return years
}
