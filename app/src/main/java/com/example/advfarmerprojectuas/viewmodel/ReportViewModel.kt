package com.example.advfarmerprojectuas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.advfarmerprojectuas.model.Report
import com.example.advfarmerprojectuas.model.User
import kotlinx.coroutines.CoroutineScope
import java.text.SimpleDateFormat
import java.util.*

class ReportViewModel(application: Application): AndroidViewModel(application){
    val reportLD = MutableLiveData<List<Report>>()

    fun refresh(){
        var date = Calendar.DAY_OF_MONTH
        var listDate = arrayListOf<Report>()

        val sdf = SimpleDateFormat("MMMM yyyy",)

        for (i in 1..date){
            listDate.add(Report(i.toString(),0,0.0,"a"))
        }
        reportLD.value = listDate
    }
}