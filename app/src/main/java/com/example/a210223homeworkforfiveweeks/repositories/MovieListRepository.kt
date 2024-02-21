package com.example.a210223homeworkforfiveweeks.repositories

import androidx.lifecycle.LiveData
import com.example.a210223homeworkforfiveweeks.data.MovieListDao
import com.example.a210223homeworkforfiveweeks.models.MovieListModel

class MovieListRepository (private val movielistDao: MovieListDao) {

    val movielist = movielistDao.getAllMovieList()
    //метод получение всех данных из категории
    // когда не нужно нечего выбирать и фильтровать

    fun getFilter (MovieTime:String, MovieRating:String):
            //фильтр идет по двум категориям MovieTime MovieRating
    // так же как написано в MovieListDao
            LiveData<List<MovieListModel>> {
        return movielistDao.getFilter(MovieTime, MovieRating)
        //метод получения используя фильтр  категории
    }


    suspend fun insertMovieList(movie_listModel: MovieListModel){
        movielistDao.insertMovieList(movie_listModel)
        //метод создания категории
    }

    suspend fun updateMovieList(movie_listModel: MovieListModel){
        movielistDao.updateMovieList(movie_listModel)
        //метод обнавления категории
    }

    suspend fun deleteMovieList(movie_listModel: MovieListModel) {
        movielistDao.deleteMovieList(movie_listModel)
        //метод удаления одной категории
    }

    suspend fun deleteAllMovieList(){
        movielistDao.deleteAllMovieList()
        //метод удаления всех категории
    }
}