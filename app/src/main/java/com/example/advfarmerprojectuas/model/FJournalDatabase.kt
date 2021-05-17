package com.example.advfarmerprojectuas.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class, Log::class), version = 1)
abstract class FJournalDatabase:RoomDatabase() {
    abstract fun FJournalDao():FJournalDao

    companion object{
        @Volatile private var instance:FJournalDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FJournalDatabase::class.java,
            "fjournaldb"
        )
            .build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
    }
}