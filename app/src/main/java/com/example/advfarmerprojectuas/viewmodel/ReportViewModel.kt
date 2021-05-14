package com.example.advfarmerprojectuas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.advfarmerprojectuas.model.Report
import com.example.advfarmerprojectuas.util.dateFormat
import java.text.SimpleDateFormat
import java.util.*

class ReportViewModel(application: Application): AndroidViewModel(application){
    val reportLD = MutableLiveData<List<Report>>()

    fun refresh(){
        var date = Calendar.getInstance();
        var mycal = GregorianCalendar(Calendar.YEAR, Calendar.MONTH, 1)
        var daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH)

        var listDate = arrayListOf<Report>()


        for (i in 1..daysInMonth){
            var tanggal = dateFormat()
            listDate.add(Report("$i $tanggal ", 0, 0.0, "a"))
        }
        reportLD.value = listDate
    }
}