package com.example.advfarmerprojectuas.viewmodel

import android.app.Application
import android.util.Log
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
    val userLD = MutableLiveData<User>()
    val welcomeLD = MutableLiveData<Boolean>()
    private var job = Job()

    fun addUser(user: User){
        var bmr = 0.0;
        if (user.gender == 0) {
            bmr = (13.97 * user.weight) + (4.799 * user.height) - (5.677 * user.umur) + 88.362
        } else {
            bmr = (9.247 * user.weight) + (3.098 * user.height) - (4.330 * user.umur) + 447.593
        }

        if (user.pgoal == "Maintain") {
            user.target = bmr.toInt()
        } else if (user.pgoal == "Gain") {
            user.target = (bmr + (bmr * 15/ 100)).toInt()
        } else if (user.pgoal == "Loss") {
            user.target = (bmr - (bmr * 15 / 100)).toInt()
        }

        launch {
            val db = buildDB(getApplication())
            db.FJournalDao().insertAll(user)
        }
    }

    fun fetchUser(){
        launch {
            val db = buildDB(getApplication())
            userLD.value = db.FJournalDao().selectUser()
        }
    }

    fun welcomeUserCheck() {
        launch {
            val db = buildDB(getApplication())
            userLD.value = db.FJournalDao().selectUser()
            if (userLD.value == null) {
                welcomeLD.value = true
            } else {
                welcomeLD.value = false
            }
        }
    }

    fun updateUserProfile(nama:String, umur:Int, gender:Int, weight:Int, height:Int, uuid:Int) {
        launch {
            val db = buildDB(getApplication())
            db.FJournalDao().updateUserProfile(nama, umur, gender, weight, height, uuid)
        }
    }


    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}