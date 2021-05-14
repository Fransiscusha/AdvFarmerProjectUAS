package com.example.advfarmerprojectuas.util

import android.content.Context
import androidx.room.Room
import com.example.advfarmerprojectuas.model.FJournalDatabase

val DB_NAME = "fjournaldb"

fun buildDB(context: Context):FJournalDatabase{
    val db = Room.databaseBuilder(context, FJournalDatabase::class.java, DB_NAME)
        .addMigrations()
        .build()
    return db
}