package com.example.advfarmerprojectuas.util

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.advfarmerprojectuas.model.FJournalDatabase
import java.text.SimpleDateFormat
import java.util.*

val DB_NAME = "fjournaldb"

fun buildDB(context: Context):FJournalDatabase{
    val db = Room.databaseBuilder(context, FJournalDatabase::class.java, DB_NAME)
        .addMigrations()
        .build()
    return db
}

fun dateFormat():String{
    val sdf = SimpleDateFormat("MMMM yyyy",Locale.getDefault())
    return sdf.format(Date())
}