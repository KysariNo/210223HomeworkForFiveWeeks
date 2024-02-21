package com.example.a210223homeworkforfiveweeks.repositories

import com.example.a210223homeworkforfiveweeks.data.MovieGenresDao
import com.example.a210223homeworkforfiveweeks.models.MovieGenresModel

class MovieGenresRepository (private val moviegenresDao: MovieGenresDao) {

    val moviegenres = moviegenresDao.getAllMovieGenresY()
     //метод получение всех данных из категории
    // когда не нужно нечего выбирать и фильтровать



    suspend fun insertMovieGenres(movie_genresModel: MovieGenresModel){
        moviegenresDao.insertMovieGenres(movie_genresModel)
            //метод создания категории
    }

    suspend fun updateMovieGenres(movie_genresModel: MovieGenresModel){
        moviegenresDao.updateMovieGenres(movie_genresModel)
        //метод обнавления категории
    }

    suspend fun deleteMovieGenres(movie_genresModel: MovieGenresModel) {
        moviegenresDao.deleteMovieGenres(movie_genresModel)
        //метод удаления одной категории
    }

    suspend fun deleteAllMovieGenres(){
        moviegenresDao.deleteAllMovieGenresY()
        //метод удаления всех категории
    }
}