package com.example.advfarmerprojectuas.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    var nama:String,
    var umur:Int,
    var gender:String,
    var weight:Int,
    var height:Int,
    var pgoal:String,
    var target: Double,
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}

@Entity
data class Log(
    var tanggal:String,
    var nama: String,
    var kalori:Double,
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}

data class Report(
    var tanggal:String?,
    var jMeal:Int?,
    var jCal : Double?,
    var status: String?,
)