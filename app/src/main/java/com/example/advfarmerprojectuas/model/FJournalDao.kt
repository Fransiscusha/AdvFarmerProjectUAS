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

    @Query("UPDATE user SET nama=:nama, umur=:umur, gender=:gender, weight=:weight, height=:height WHERE uuid=:uuid")
    suspend fun updateUserProfile(nama:String, umur:Int, gender:String, weight:Int, height:Int, uuid:Int)
}