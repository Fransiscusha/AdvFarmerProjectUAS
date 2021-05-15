package com.example.advfarmerprojectuas.view

import android.view.View
import com.example.advfarmerprojectuas.model.Log
import com.example.advfarmerprojectuas.model.User

interface UserSaveProfileListener{
    fun onUserSaveProfile(v:View, obj:User)
}

interface AgeRadioClickListener{
    fun onAgeRadioClick(v:View, obj:User)
}

interface CreateUserClickListener{
    fun onCreateUserClick(v:View, obj:User)
}

interface LogAMealListener{
    fun onLogAMeal(v:View)
}

interface CreateLogCliclListener{
    fun onCreateLogClick(v:View, obj: Log)
}