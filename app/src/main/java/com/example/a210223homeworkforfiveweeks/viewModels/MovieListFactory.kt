package com.example.a210223homeworkforfiveweeks.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a210223homeworkforfiveweeks.repositories.MovieListRepository

class MovieListFactory  constructor(private val movie_listRepository: MovieListRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MovieListViewModels::class.java)) {
            MovieListViewModels(this.movie_listRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}