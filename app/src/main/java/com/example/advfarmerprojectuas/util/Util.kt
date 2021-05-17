package com.example.advfarmerprojectuas.util

import android.content.Context
import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.room.Room
import com.example.advfarmerprojectuas.model.FJournalDatabase
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_report.*
import java.text.SimpleDateFormat
import java.util.*

val DB_NAME = "fjournaldb"

fun buildDB(context: Context):FJournalDatabase{
    val db = Room.databaseBuilder(context, FJournalDatabase::class.java, DB_NAME)
        .build()
    return db
}

fun dateFormat():String{
    val sdf = SimpleDateFormat("MMMM yyyy",Locale.getDefault())
    return sdf.format(Date())
}

fun getCurrentDate():String{
    val getCurrentDate = SimpleDateFormat("dd MMMM yyyy").format(Date())
    return getCurrentDate
}

@BindingAdapter("android:vl","android:maxAxis")
fun makeGraph(lc:LineChart, vl: LineDataSet?, x: String?){
    if(vl != null && x!= null) {
        vl.setDrawValues(false)
        vl.lineWidth = 3f
        lc.data = LineData(vl)
        lc.xAxis.axisMaximum = x.toFloat()
    }
    lc.axisRight.isEnabled = false
    lc.setTouchEnabled(true)
    lc.setPinchZoom(true)
    lc.description.text = "Days"
    lc.setNoDataText("No data yet!")
    lc.animateX(1800, Easing.EaseInExpo)
}