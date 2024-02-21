package com.example.a210223homeworkforfiveweeks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a210223homeworkforfiveweeks.models.MovieGenresModel
import com.example.a210223homeworkforfiveweeks.models.MovieListModel

@Database(entities = [MovieGenresModel::class, MovieListModel::class],version = 1)
abstract class Database: RoomDatabase() {

    abstract val moviegenresDAO : MovieGenresDao
    abstract val movielistDAO : MovieListDao

    companion object{
        @Volatile
        private var INSTANCE : com.example.a210223homeworkforfiveweeks.data.Database? = null
        fun getInstance(context: Context):com.example.a210223homeworkforfiveweeks.data.Database{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        com.example.a210223homeworkforfiveweeks.data.Database::class.java,
                        "database"
                    ).build()
                }
                return instance
            }
        }

    }

}