package com.example.advfarmerprojectuas.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.advfarmerprojectuas.model.Report
import com.example.advfarmerprojectuas.util.buildDB
import com.example.advfarmerprojectuas.util.dateFormat
import com.github.mikephil.charting.data.Entry
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
    var entriesLD = MutableLiveData<List<Entry>>()
    private var job = Job()

    fun refresh(){
        var tanggal = dateFormat()
        var entry:ArrayList<Entry> = arrayListOf()
        var report:List<Report> = arrayListOf()
        var target:Int = 0
        var listDate = arrayListOf<Report>()
        reportLD.value = listDate
        launch {
            val db = buildDB(getApplication())
            report = db.FJournalDao().selectReport("% $tanggal")
            Log.d("cekrdb",report.toString())
            var user = db.FJournalDao().selectUser()
            target = user.target
            var calendar = Calendar.getInstance()
            var daysInMonth = calendar.getActualMaximum(Calendar.DATE)

            for (i in 1..daysInMonth){
                var ada = false
                var sreport = Report("$i $tanggal ", 0, 0.0, "LOW")
                if(report.isNotEmpty()){
                    Log.d("cekr","ada")
                    for(r in report){
                        if(r.tanggal == "$i $tanggal"){
                            ada = true
                            sreport = r
                            break
                        }
                    }
                    if(ada){
                        when {
                            sreport.jCal!!>target -> {
                                listDate.add(Report("$i $tanggal ", sreport.jMeal, sreport.jCal, "EXCEED"))
                            }
                            sreport.jCal!!>(0.5*target) -> {
                                listDate.add(Report("$i $tanggal ", sreport.jMeal, sreport.jCal, "NORMAL"))
                            }
                            else -> {
                                listDate.add(Report("$i $tanggal ", sreport.jMeal, sreport.jCal, "LOW"))
                            }
                        }
                        entry.add(Entry(i.toFloat(), sreport.jCal.toString().toFloat()))
                    }
                    else{
                        listDate.add(sreport)
                        entry.add(Entry(i.toFloat(), 0f))
                    }
                } else{
                    listDate.add(Report("$i $tanggal ", 0, 0.0, "LOW"))
                }

            }
            Log.d("cekreport",listDate.toString())

            reportLD.value = listDate
            entriesLD.value = entry
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}