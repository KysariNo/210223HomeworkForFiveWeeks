package com.example.a210223homeworkforfiveweeks.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_genres_data_table")
//название базы данных
data class MovieGenresModel (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "genres_id")
    var id : Int,
    //айдишник генирируеться автоматически

    @ColumnInfo(name = "genres_name")
    var name : String

)