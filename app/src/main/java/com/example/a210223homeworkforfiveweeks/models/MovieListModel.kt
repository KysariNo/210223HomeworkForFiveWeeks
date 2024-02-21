package com.example.a210223homeworkforfiveweeks.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_list_data_table")
data class MovieListModel (
    //название базы данных

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movie_id")
    var id : Int,
    //айдишник генирируеться автоматически

    @ColumnInfo(name = "movie_name")
    var name : String,

    @ColumnInfo(name = "movie_genres")
    var genres : String,

    @ColumnInfo(name = "movie_time")
    var time : Int,

    @ColumnInfo(name = "movie_rating")
    var rating : Int

    //поля в базе данных

)