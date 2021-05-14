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
    val currentCaloriesLD = MutableLiveData<Int>()
    private var job = Job()

    fun fetchLog(){
        launch {
            val db = buildDB(getApplication())
            logLD.value = db.FJournalDao().selectLog(SimpleDateFormat("dd").format(Date()))
        }
    }

    fun getCurrentCalories(){
        launch {
            val db = buildDB(getApplication())
            currentCaloriesLD.value = db.FJournalDao().getCurrentCalories(SimpleDateFormat("dd").format(Date()))
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}