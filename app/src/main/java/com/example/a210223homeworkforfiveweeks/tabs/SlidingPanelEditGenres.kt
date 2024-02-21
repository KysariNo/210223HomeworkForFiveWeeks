package com.example.a210223homeworkforfiveweeks.tabs

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.a210223homeworkforfiveweeks.R
import com.example.a210223homeworkforfiveweeks.data.Database
import com.example.a210223homeworkforfiveweeks.databinding.SlidingPanelEditGenresBinding
import com.example.a210223homeworkforfiveweeks.repositories.MovieGenresRepository
import com.example.a210223homeworkforfiveweeks.viewModels.MovieGenresFactory
import com.example.a210223homeworkforfiveweeks.viewModels.MovieGenresViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SlidingPanelEditGenres : BottomSheetDialogFragment(),View.OnKeyListener {

    private var binding: SlidingPanelEditGenresBinding? = null
    private var moviegenresRepository: MovieGenresRepository? = null
    private var moviegenresViewModel: MovieGenresViewModels? = null
    private var factory: MovieGenresFactory? = null
    private var idCategory:Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SlidingPanelEditGenresBinding.inflate(inflater, container, false)
       //idCategory узнать где эта переменная
        idCategory = arguments?.getString("MovieGenres_id")?.toInt()
        binding?.EditMovieGenres?.setText(arguments?.getString("MovieGenres_name").toString())

        val moviegenresDao = Database.getInstance((context as FragmentActivity).application).moviegenresDAO
        moviegenresRepository = MovieGenresRepository(moviegenresDao)
        factory = MovieGenresFactory(moviegenresRepository!!)
        moviegenresViewModel = ViewModelProvider(this,factory!!).get(MovieGenresViewModels::class.java)

        binding?.EditMovieGenres?.setOnKeyListener(this)

        return binding?.root
    }

    override fun onKey(view: View, i: Int, keyEvent: KeyEvent): Boolean {
        when (view.id) {


            R.id.EditMovieGenres -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {

                    moviegenresViewModel?.startupdateMovieGenres(idCategory.toString().toInt(), binding?.EditMovieGenres?.text?.toString()!!)

                    binding?.EditMovieGenres?.setText("")

                    dismiss()

                    (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(R.id.content, TabDeleteGenresItemBottomNav()).commit()

                    return true
                }

            }
        }

        return false
    }

}