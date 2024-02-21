package com.example.a210223homeworkforfiveweeks.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a210223homeworkforfiveweeks.repositories.MovieGenresRepository

class MovieGenresFactory constructor(private val moviegenresRepository: MovieGenresRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MovieGenresViewModels::class.java)) {
            MovieGenresViewModels(this.moviegenresRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}