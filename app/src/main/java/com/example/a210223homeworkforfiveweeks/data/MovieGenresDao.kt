package com.example.a210223homeworkforfiveweeks.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.a210223homeworkforfiveweeks.models.MovieGenresModel

@Dao
interface MovieGenresDao {
   //suspend команда для принудительной остановки карутин

    @Insert
    suspend fun insertMovieGenres(movie_genresModel: MovieGenresModel)
    //метод создания записи в базу данных

    @Update
    suspend fun updateMovieGenres(movie_genresModel: MovieGenresModel)
    //метод обновления записи в базу данных

    @Delete
    suspend fun deleteMovieGenres(movie_genresModel: MovieGenresModel)
    //метод удаления записи в базе данных

    @Query("DELETE FROM movie_genres_data_table")
    suspend fun deleteAllMovieGenresY()
    //метод удаление всех записей в базуеданных

    @Query("SELECT * FROM movie_genres_data_table")
    fun getAllMovieGenresY(): LiveData<List<MovieGenresModel>>
    //метод получение всех записей в базе данных

}