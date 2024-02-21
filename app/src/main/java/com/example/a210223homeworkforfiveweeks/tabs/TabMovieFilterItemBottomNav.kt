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
import com.example.a210223homeworkforfiveweeks.databinding.TabMovieFilterItemBottomNavBinding
import com.example.a210223homeworkforfiveweeks.models.MovieListModel
import com.example.a210223homeworkforfiveweeks.repositories.MovieListRepository
import com.example.a210223homeworkforfiveweeks.viewModels.MovieListFactory
import com.example.a210223homeworkforfiveweeks.viewModels.MovieListViewModels


class TabMovieFilterItemBottomNav : Fragment() {

    private var binding : TabMovieFilterItemBottomNavBinding? = null
    private var movielistRepository: MovieListRepository? = null
    private var movielisViewModel: MovieListViewModels? = null
    private var movielistFactory: MovieListFactory? = null
    private var movielistAdapter: MovieListAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TabMovieFilterItemBottomNavBinding.inflate(inflater, container, false)

        val movielistDao = Database.getInstance((context as FragmentActivity).application).movielistDAO
        movielistRepository = MovieListRepository(movielistDao)
        movielistFactory = MovieListFactory(movielistRepository!!)
        movielisViewModel = ViewModelProvider(this, movielistFactory!!).get(MovieListViewModels::class.java)
        initRecyclerMovieList()




        return binding?.root
    }

    private fun initRecyclerMovieList(){
        binding?.recyclerFilterMovie?.layoutManager = LinearLayoutManager(context)
        movielistAdapter = MovieListAdapter({productModel: MovieListModel ->deleteMovieList(productModel)},
            {productModel: MovieListModel ->SlidingPanelEditMovie(productModel)})
        binding?.recyclerFilterMovie?.adapter = movielistAdapter

        displayFilterMovie()
    }

    private fun displayFilterMovie(){
        movielisViewModel?.getFilter("120", "7")?.observe(viewLifecycleOwner,
            Observer {
            movielistAdapter?.setList(it)
            movielistAdapter?.notifyDataSetChanged()
        })
    }

    private fun deleteMovieList(productModel:MovieListModel) {
        movielisViewModel?.deleteMovieList(productModel)
    }

    private fun SlidingPanelEditMovie(movielisModel:MovieListModel) {
        val SlidingPanelEditMovie = SlidingPanelEditMovie()
        val parameters = Bundle()
        parameters.putString("movielist_id", movielisModel.id.toString())
        parameters.putString("movielist_name", movielisModel.name)
        parameters.putString("movielist_genres", movielisModel.genres)
        //надо сделать так чтоб пользователь мог вводить только цифры
        parameters.putString("movielist_time", movielisModel.time.toString())
        parameters.putString("movielist_rating", movielisModel.rating.toString())

        SlidingPanelEditMovie.arguments = parameters

        SlidingPanelEditMovie.show((context as FragmentActivity).supportFragmentManager, "editProduct")
    }


}