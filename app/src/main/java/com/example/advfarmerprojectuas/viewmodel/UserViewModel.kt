package com.example.advfarmerprojectuas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.advfarmerprojectuas.model.User
import com.example.advfarmerprojectuas.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val userLD = MutableLiveData<List<User>>()
    private var job = Job()

    fun addUser(user: User){
        launch {
            val db = buildDB(getApplication())
            db.FJournalDao().insertAll(user)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}