package com.example.a210223homeworkforfiveweeks.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a210223homeworkforfiveweeks.models.MovieGenresModel
import com.example.a210223homeworkforfiveweeks.repositories.MovieGenresRepository
import kotlinx.coroutines.launch

class MovieGenresViewModels(private val moviegenresRepository: MovieGenresRepository) : ViewModel() {

    val moviegenres = moviegenresRepository.moviegenres
    //получение всех данных из базы данных

    fun startInsert(nameMovieGenres:String) {
        //эта функция создания жарна
        insert(MovieGenresModel(0, nameMovieGenres))
        //в скобках указываем данные которые нужно использовать
    // для создание категорий а именно айди и  имя жанра
        //айди автоматический поэтому ноль 0
    }

    fun startupdateMovieGenres(idMovieGenres:Int, nameMovieGenres:String) {
        //эта функция обнавления жанра
        updateMovieGenres(MovieGenresModel(idMovieGenres, nameMovieGenres))
        //в скобках указываем данные которые нужно использовать
        // для создание категорий а именно айди и  имя жанра
    }

    fun insert(moviegenresModel: MovieGenresModel) = viewModelScope.launch{
//запись в базу данных
        moviegenresRepository.insertMovieGenres(moviegenresModel)
    }

    fun updateMovieGenres(moviegenresModel: MovieGenresModel) = viewModelScope.launch{
//обновление в базе данных
        moviegenresRepository.updateMovieGenres(moviegenresModel)
    }

    fun delete(moviegenresModel: MovieGenresModel) = viewModelScope.launch{
//удаление одного жанра в базе данных
        moviegenresRepository.deleteMovieGenres(moviegenresModel)
    }

    fun deleteAll() = viewModelScope.launch{
//удаление всех жанров в базе данных
        moviegenresRepository.deleteAllMovieGenres()
    }


}