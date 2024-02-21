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
import com.example.a210223homeworkforfiveweeks.databinding.SlidingPanelEditMovieBinding
import com.example.a210223homeworkforfiveweeks.repositories.MovieListRepository
import com.example.a210223homeworkforfiveweeks.viewModels.MovieListFactory
import com.example.a210223homeworkforfiveweeks.viewModels.MovieListViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SlidingPanelEditMovie : BottomSheetDialogFragment(), View.OnKeyListener, View.OnClickListener {

    private var binding: SlidingPanelEditMovieBinding? = null
    private var productRepository: MovieListRepository? = null
    private var productViewModel: MovieListViewModels? = null
    private var factory: MovieListFactory? = null
    private var idProduct:Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SlidingPanelEditMovieBinding.inflate(inflater, container, false)

        idProduct = arguments?.getString("movielist_id")?.toInt()
        binding?.editMovieName?.setText(arguments?.getString("movielist_name").toString())
        binding?.editMovieGenre?.setText(arguments?.getString("movielist_genres").toString())
        binding?.editMovieTime?.setText(arguments?.getString("movielist_time").toString())
        binding?.editMovieRating?.setText(arguments?.getString("movielist_rating").toString())


        val movielistDao = Database.getInstance((context as FragmentActivity).application).movielistDAO
        productRepository = MovieListRepository(movielistDao)
        factory = MovieListFactory(productRepository!!)
        productViewModel = ViewModelProvider(this, factory!!).get(MovieListViewModels::class.java)

        binding?.editMovieName?.setOnKeyListener(this)
        binding?.editMovieGenre?.setOnKeyListener(this)
        binding?.editMovieTime?.setOnKeyListener(this)
        binding?.editMovieRating?.setOnKeyListener(this)


        binding?.buttonEditMovie?.setOnClickListener(this)


        return binding?.root
    }

    override fun onKey(view: View, i: Int, keyEvent: KeyEvent): Boolean {
        when (view.id) {


            R.id.editMovieName -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {

                    binding?.resEditNameMovie?.text = binding?.editMovieName?.text
                    binding?.editMovieName?.setText("")

                    return true
                }

            }

            R.id.editMovieGenre -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {

                    binding?.resEditMovieGenre?.text = binding?.editMovieGenre?.text
                    binding?.editMovieGenre?.setText("")

                    return true
                }

            }

            R.id.editMovieTime -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {

                    binding?.resEditMovieTime?.text = binding?.editMovieTime?.text
                    binding?.editMovieTime?.setText("")

                    return true
                }

            }

            R.id.editMovieRating -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {

                    binding?.resEditMovieRating?.text = binding?.editMovieRating?.text
                    binding?.editMovieRating?.setText("")

                    return true
                }

            }
        }

        return false
    }

    override fun onClick(view: View) {
        productViewModel?.startUpdateMovieList(idProduct.toString().toInt(), binding?.resEditNameMovie?.text?.toString()!!,
            binding?.resEditMovieGenre?.text?.toString()!!, binding?.resEditMovieTime?.text?.toString()!!.toInt(), binding?.resEditMovieRating?.text?.toString()!!.toInt())

        dismiss()

        (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(R.id.content, TabDeleteMovieItemBottomNav()).commit()
    }

}