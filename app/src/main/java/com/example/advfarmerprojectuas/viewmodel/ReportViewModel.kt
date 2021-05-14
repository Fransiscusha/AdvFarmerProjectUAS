package com.example.advfarmerprojectuas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.advfarmerprojectuas.model.Report
import com.example.advfarmerprojectuas.util.buildDB
import com.example.advfarmerprojectuas.util.dateFormat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext
import kotlin.math.tan

class ReportViewModel(application: Application): AndroidViewModel(application),CoroutineScope{
    val reportLD = MutableLiveData<List<Report>>()
    private var job = Job()

    fun refresh(){

        var tanggal = dateFormat()
        var report:List<Report> = arrayListOf()
        var target:Int = 0
        launch {
            val db = buildDB(getApplication())
            report = db.FJournalDao().selectReport(dateFormat())
            var user = db.FJournalDao().selectUser()
//            target = user.target
        }
        var date = Calendar.getInstance();
        var mycal = GregorianCalendar(Calendar.YEAR, Calendar.MONTH, 1)
        var daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH)

        var listDate = arrayListOf<Report>()


        for (i in 1..daysInMonth){
            if(report.isNotEmpty()){
                for(r in report){
                    if(r.tanggal == "$i ${tanggal}"){
                        when {
                            r.jCal!!>target -> {
                                listDate.add(Report("$i $tanggal ", r.jMeal, r.jCal, "EXCEED"))
                            }
                            r.jCal!!>(50/100*target) -> {
                                listDate.add(Report("$i $tanggal ", r.jMeal, r.jCal, "NORMAL"))
                            }
                            else -> {
                                listDate.add(Report("$i $tanggal ", r.jMeal, r.jCal, "LOW"))
                            }
                        }
                    }
                    else{
                        listDate.add(Report("$i $tanggal ", 0, 0.0, "LOW"))
                    }
                }
            } else{
                listDate.add(Report("$i $tanggal ", 0, 0.0, "LOW"))
            }

        }
        reportLD.value = listDate
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}