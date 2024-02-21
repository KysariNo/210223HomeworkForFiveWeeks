package com.example.a210223homeworkforfiveweeks.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a210223homeworkforfiveweeks.models.MovieListModel
import com.example.a210223homeworkforfiveweeks.repositories.MovieListRepository
import kotlinx.coroutines.launch

class MovieListViewModels (private val movielistRepository: MovieListRepository) : ViewModel() {

    val movielist = movielistRepository.movielist
    //получение всех данных из базы данных



    fun getFilter (timeMovieList:String, ratingMovieList:String):
            LiveData<List<MovieListModel>> {
        //эта функция фильтра фильма
        return movielistRepository.getFilter(timeMovieList, ratingMovieList)
    }

    fun startInsert(nameMovieList:String, actorsMovieList:String, timeMovieList:Int,ratingMovieList:Int) {
        insertMovieList(MovieListModel(0,nameMovieList, nameMovieList, timeMovieList,ratingMovieList))
        //в скобках указываем данные которые нужно использовать
        // для создание категорий а именно айди и  имя фильма время фильма райинг фильма
        //айди автоматический поэтому ноль 0
    }

    fun startUpdateMovieList(idMovieList:Int, nameMovieList:String, genreMovieList:String, timeMovieList:Int,ratingMovieList:Int) {
        //эта функция обнавления жанра
        updateMovieList(MovieListModel(idMovieList, nameMovieList, genreMovieList, timeMovieList,ratingMovieList))
        //в скобках указываем данные которые нужно использовать
        // для создание фильма а именно
    // айди и  имя фильма ,жанр фильма ,время фильма ,рейтинг фильма
    }

    fun insertMovieList(movielistModel: MovieListModel) = viewModelScope.launch{
//запись в базу данных

        movielistRepository.insertMovieList(movielistModel)
    }

    fun updateMovieList(movielistModel: MovieListModel) = viewModelScope.launch{
//обновление в базе данных

        movielistRepository.updateMovieList(movielistModel)
    }

    fun deleteMovieList(movielistModel: MovieListModel) = viewModelScope.launch{
//удаление одного жанра в базе данных

        movielistRepository.deleteMovieList(movielistModel)
    }

    fun deleteAllMovieList() = viewModelScope.launch{
//удаление всех жанров в базе данных

        movielistRepository.deleteAllMovieList()
    }


}