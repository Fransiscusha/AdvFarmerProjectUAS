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
        user.target = updateTarget(user.gender, user.weight, user.height, user.umur, user.pgoal)

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

    fun updateUserProfile(nama:String, umur:Int, gender:Int, weight:Int, height:Int, pgoal:String, uuid:Int) {
        val target = updateTarget(gender, weight, height, umur, pgoal)
        launch {
            val db = buildDB(getApplication())
            db.FJournalDao().updateUserProfile(nama, umur, gender, weight, height, target, uuid)
        }
    }

    fun updateTarget(gender:Int, weight:Int, height:Int, umur: Int, pgoal:String):Int {
        var bmr = 0.0;
        if (gender == 0) {
            bmr = (13.97 * weight) + (4.799 * height) - (5.677 * umur) + 88.362
        } else {
            bmr = (9.247 * weight) + (3.098 * height) - (4.330 * umur) + 447.593
        }
        var target = 0;
        if (pgoal == "Maintain") {
            target = bmr.toInt()
        } else if (pgoal == "Gain") {
            target = (bmr + (bmr * 15/ 100)).toInt()
        } else if (pgoal == "Loss") {
            target = (bmr - (bmr * 15 / 100)).toInt()
        }
        return target
    }


    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}