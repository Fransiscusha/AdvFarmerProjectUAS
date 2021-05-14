package com.example.advfarmerprojectuas.view

import android.view.View
import com.example.advfarmerprojectuas.model.User

interface UserSaveProfileListener{
    fun onUserSaveProfile(v:View, obj:User)
}

interface LogAMealListener{
    fun onLogAMeal(v:View)
}