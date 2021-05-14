package com.example.advfarmerprojectuas.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FJournalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg user:User)

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun selectUser():User

    @Query("SELECT tanggal, SUM(kalori) , count(kalori) FROM log where tanggal = :tgl GROUP BY tanggal ")
    suspend fun selectReport(tgl:String):List<Report>
}