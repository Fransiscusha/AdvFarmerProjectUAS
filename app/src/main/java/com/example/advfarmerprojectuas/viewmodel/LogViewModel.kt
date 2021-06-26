package com.example.advfarmerprojectuas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.advfarmerprojectuas.model.Log
import com.example.advfarmerprojectuas.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

class LogViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val logLD = MutableLiveData<List<Log>>()
    val slogLd = MutableLiveData<Log>()
    val currentCaloriesLD = MutableLiveData<Int>()
    val needCaloriesLD = MutableLiveData<Int>()
    val statusLD = MutableLiveData<String>()

    private var job = Job()

    fun declareLog() {
        slogLd.value = Log("", "", 0.0)
    }

    fun addLog(nama:String, cal:Double){
        launch {
            val log = Log(SimpleDateFormat("d MMMM yyyy").format(Date()), nama, cal)
            val db = buildDB(getApplication())
            db.FJournalDao().insertLogMeal(log)
            getNeedCalories()
        }
    }

    fun fetchLog(){
        launch {
            val db = buildDB(getApplication())
            logLD.value = db.FJournalDao().selectLog(SimpleDateFormat("d MMMM yyyy").format(Date()))
        }
    }

    fun getCurrentCalories(){
        launch {
            val db = buildDB(getApplication())
            currentCaloriesLD.value = if (db.FJournalDao().getCurrentCalories(SimpleDateFormat("d MMMM yyyy").format(Date())) == null) 0 else db.FJournalDao().getCurrentCalories(SimpleDateFormat("d MMMM yyyy").format(Date()))
        }
    }

    fun getNeedCalories() {
        launch {
            val db = buildDB(getApplication())
            val currCalories = if (db.FJournalDao().getCurrentCalories(SimpleDateFormat("d MMMM yyyy").format(Date())) == null) 0 else db.FJournalDao().getCurrentCalories(SimpleDateFormat("d MMMM yyyy").format(Date()))
            val target = db.FJournalDao().selectUser().target
            needCaloriesLD.value = if ((target - currCalories) < 0) 0 else (target - currCalories)
        }
    }

    fun getStatus(){
        launch {
            val db = buildDB(getApplication())
            val currCalories = if (db.FJournalDao().getCurrentCalories(SimpleDateFormat("d MMMM yyyy").format(Date())) == null) 0 else db.FJournalDao().getCurrentCalories(SimpleDateFormat("d MMMM yyyy").format(Date()))
            val target = db.FJournalDao().selectUser().target

            when{
                currCalories > target -> statusLD.value = "EXCEED"
                currCalories > 0.5 * target -> statusLD.value = "NORMAL"
                else -> statusLD.value = "LOW"
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}