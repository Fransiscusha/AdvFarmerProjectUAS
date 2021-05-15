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
    val statusLD = MutableLiveData<String>()

    private var job = Job()

    fun addLog(nama:String, cal:Double){
        val log = Log(SimpleDateFormat("yyyy mm dd").format(Date()), nama, cal)
        launch {
            val db = buildDB(getApplication())
            db.FJournalDao().insertLogMeal(log)
        }
    }

    fun fetchLog(){
        launch {
            val db = buildDB(getApplication())
            logLD.value = db.FJournalDao().selectLog(SimpleDateFormat("dd").format(Date()))
        }
    }

    fun getCurrentCalories(){
        launch {
            val db = buildDB(getApplication())
            currentCaloriesLD.value = if (db.FJournalDao().getCurrentCalories(SimpleDateFormat("dd").format(Date())) == null) 0 else db.FJournalDao().getCurrentCalories(SimpleDateFormat("dd").format(Date()))
        }
    }

    fun getStatus(){
        launch {
            val db = buildDB(getApplication())
            val currCalories = if (db.FJournalDao().getCurrentCalories(SimpleDateFormat("dd").format(Date())) == null) 0 else db.FJournalDao().getCurrentCalories(SimpleDateFormat("dd").format(Date()))
            //val target = db.FJournalDao().selectUser().target
            val target = 100

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