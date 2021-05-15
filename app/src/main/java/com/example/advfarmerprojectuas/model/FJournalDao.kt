package com.example.advfarmerprojectuas.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FJournalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg user:User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLogMeal(vararg log:Log)

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun selectUser():User

    @Query("UPDATE user SET nama=:nama, umur=:umur, gender=:gender, weight=:weight, height=:height WHERE uuid=:uuid")
    suspend fun updateUserProfile(nama:String, umur:Int, gender:Int, weight:Int, height:Int, uuid:Int)

    @Query("SELECT * FROM log WHERE tanggal= :tanggal")
    suspend fun selectLog(tanggal:String):List<Log>

    @Query("SELECT SUM(kalori) FROM log WHERE tanggal= :tanggal")
    suspend fun  getCurrentCalories(tanggal: String): Int

    @Query("SELECT tanggal, SUM(kalori) , count(kalori) FROM log where tanggal = :tgl GROUP BY tanggal ")
    suspend fun selectReport(tgl:String):List<Report>

}