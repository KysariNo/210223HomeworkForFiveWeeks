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
import com.example.a210223homeworkforfiveweeks.databinding.TabDeleteMovieItemBottomNavBinding
import com.example.a210223homeworkforfiveweeks.models.MovieListModel
import com.example.a210223homeworkforfiveweeks.repositories.MovieListRepository
import com.example.a210223homeworkforfiveweeks.viewModels.MovieListFactory
import com.example.a210223homeworkforfiveweeks.viewModels.MovieListViewModels


class TabDeleteMovieItemBottomNav  : Fragment() {

    private var binding: TabDeleteMovieItemBottomNavBinding? = null
    private var movielistRepository: MovieListRepository? = null
    private var movielistViewModel: MovieListViewModels? = null
    private var movielistFactory: MovieListFactory? = null
    private var movielistAdapter: MovieListAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TabDeleteMovieItemBottomNavBinding.inflate(inflater, container, false)

        val movielistDao = Database.getInstance((context as FragmentActivity).application).movielistDAO
        movielistRepository = MovieListRepository(movielistDao)
        movielistFactory = MovieListFactory(movielistRepository!!)
        movielistViewModel = ViewModelProvider(this, movielistFactory!!).get(MovieListViewModels::class.java)
        initRecyclerMovieList()

        binding?.deleteAllMovie?.setOnClickListener(View.OnClickListener {
            movielistViewModel?.deleteAllMovieList()
        })

        return binding?.root
    }

    private fun initRecyclerMovieList(){
        binding?.recyclerMovie?.layoutManager = LinearLayoutManager(context)
        movielistAdapter = MovieListAdapter({movielistModel: MovieListModel ->deleteMovieList(movielistModel)},
            {productModel: MovieListModel ->editMovie(productModel)})
        binding?.recyclerMovie?.adapter = movielistAdapter

        displayMovie()
    }

    private fun deleteMovieList(movielistModel: MovieListModel) {
        movielistViewModel?.deleteMovieList(movielistModel)

    }

    private fun displayMovie(){
        movielistViewModel?.movielist?.observe(viewLifecycleOwner, Observer {
            movielistAdapter?.setList(it)
            movielistAdapter?.notifyDataSetChanged()
        })
    }

    private fun deleteMovie(productModel:MovieListModel) {
        movielistViewModel?.deleteMovieList(productModel)
    }

    private fun editMovie(movielistModel:MovieListModel) {
        val panelEditProduct = SlidingPanelEditMovie()
        val parameters = Bundle()
        parameters.putString("movielist_id", movielistModel.id.toString())
        parameters.putString("movielist_name", movielistModel.name)
        parameters.putString("movielist_genres", movielistModel.genres)
        parameters.putString("movielist_time", movielistModel.time.toString())
        parameters.putString("movielist_rating", movielistModel.rating.toString())
        panelEditProduct.arguments = parameters

        panelEditProduct.show((context as FragmentActivity).supportFragmentManager, "editMovieList")
    }




}