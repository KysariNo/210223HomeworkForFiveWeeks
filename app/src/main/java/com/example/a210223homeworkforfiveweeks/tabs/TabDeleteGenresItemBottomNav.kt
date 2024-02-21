package com.example.a210223homeworkforfiveweeks.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a210223homeworkforfiveweeks.data.Database
import com.example.a210223homeworkforfiveweeks.databinding.TabDeleteGenresItemBottomNavBinding
import com.example.a210223homeworkforfiveweeks.models.MovieGenresModel
import com.example.a210223homeworkforfiveweeks.repositories.MovieGenresRepository
import com.example.a210223homeworkforfiveweeks.viewModels.MovieGenresFactory
import com.example.a210223homeworkforfiveweeks.viewModels.MovieGenresViewModels


class TabDeleteGenresItemBottomNav : Fragment() {

    private var binding: TabDeleteGenresItemBottomNavBinding? = null
    private var moviegenresRepository: MovieGenresRepository? = null
    private var moviegenresViewModels: MovieGenresViewModels? = null
    private var moviegenresFactory: MovieGenresFactory? = null
    private var moviegenresAdapter: MovieGenresAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = TabDeleteGenresItemBottomNavBinding.inflate(inflater, container, false)

        val moviegenresDao = Database.getInstance((context as FragmentActivity).application).moviegenresDAO
        moviegenresRepository = MovieGenresRepository(moviegenresDao)
        moviegenresFactory = MovieGenresFactory(moviegenresRepository!!)
        moviegenresViewModels = ViewModelProvider(this, moviegenresFactory!!).get(MovieGenresViewModels::class.java)

        initRecyclerMovieGenres()
        displayMovieGenres()

        binding?.deleteAllMovieGenres?.setOnClickListener(View.OnClickListener {
            moviegenresViewModels?.deleteAll()
            //удаление всех жанров
        })


        return  binding?.root
    }

    private fun initRecyclerMovieGenres(){
        binding?.recyclerMovieGenres?.layoutManager = LinearLayoutManager(context)
        moviegenresAdapter = MovieGenresAdapter({MovieGenresModel: MovieGenresModel ->deleteMovieGenres(MovieGenresModel)},
            {moviegenresModel:MovieGenresModel->editMovieGenres(moviegenresModel)})
        binding?.recyclerMovieGenres?.adapter = moviegenresAdapter


    }

    private fun displayMovieGenres(){
        moviegenresViewModels?.moviegenres?.observe(viewLifecycleOwner, Observer {
            moviegenresAdapter?.setList(it)
            moviegenresAdapter?.notifyDataSetChanged()
        })
    }


    private fun deleteMovieGenres(moviegenresModel: MovieGenresModel) {
        moviegenresViewModels?.delete(moviegenresModel)
        //удаление только одного жанра
    }

    private fun editMovieGenres(moviegenresModel: MovieGenresModel) {
        val panelMovieGenres =SlidingPanelEditGenres()
        val parameters = Bundle()
        parameters.putString("MovieGenres_id", moviegenresModel.id.toString())
        parameters.putString("MovieGenres_name", moviegenresModel.name)
        panelMovieGenres.arguments = parameters

        panelMovieGenres.show((context as FragmentActivity).supportFragmentManager, "EditMovieGenres")
    }


}