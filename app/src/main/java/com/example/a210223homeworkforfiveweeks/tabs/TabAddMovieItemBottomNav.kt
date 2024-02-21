package com.example.a210223homeworkforfiveweeks.tabs
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.a210223homeworkforfiveweeks.R
import com.example.a210223homeworkforfiveweeks.data.Database
import com.example.a210223homeworkforfiveweeks.databinding.TabAddMovieItemBottomNavBinding
import com.example.a210223homeworkforfiveweeks.repositories.MovieGenresRepository
import com.example.a210223homeworkforfiveweeks.repositories.MovieListRepository
import com.example.a210223homeworkforfiveweeks.viewModels.MovieGenresFactory
import com.example.a210223homeworkforfiveweeks.viewModels.MovieGenresViewModels
import com.example.a210223homeworkforfiveweeks.viewModels.MovieListFactory
import com.example.a210223homeworkforfiveweeks.viewModels.MovieListViewModels

class TabAddMovieItemBottomNav : Fragment(), View.OnClickListener, View.OnKeyListener {

    private var binding: TabAddMovieItemBottomNavBinding? = null
    private var moviegenresRepository: MovieGenresRepository? = null
    private var moviegenresViewModels: MovieGenresViewModels? = null
    private var moviegenresFactory: MovieGenresFactory? = null

    private var movielistRepository: MovieListRepository? = null
    private var movielistViewModels: MovieListViewModels? = null
    private var movielistFactory: MovieListFactory? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TabAddMovieItemBottomNavBinding.inflate(inflater, container, false)

        val movie_genresDao = Database.getInstance((context as FragmentActivity).application).moviegenresDAO
        moviegenresRepository = MovieGenresRepository(movie_genresDao)
        moviegenresFactory = MovieGenresFactory(moviegenresRepository!!)
        moviegenresViewModels = ViewModelProvider(this, moviegenresFactory!!).get(MovieGenresViewModels::class.java)

        val movielistDao = Database.getInstance((context as FragmentActivity).application).movielistDAO
        movielistRepository = MovieListRepository(movielistDao)
        movielistFactory = MovieListFactory(movielistRepository!!)
        movielistViewModels= ViewModelProvider(this,movielistFactory!!).get(MovieListViewModels::class.java)

        binding?.EnterNameMovie?.setOnKeyListener(this)
        binding?.EnterNewMovieGenre?.setOnKeyListener(this)
        binding?.EnterMovieTime?.setOnKeyListener(this)
        binding?.EnterMovieRating?.setOnKeyListener(this)

        binding?.buttonAddMovie?.setOnClickListener(this)

        binding?.buttonAddHistorical?.setOnClickListener(this)
        binding?.buttonAddFantasy?.setOnClickListener(this)
        binding?.buttonAddAdventures?.setOnClickListener(this)

        return binding?.root
    }


    override fun onKey(view: View, i: Int, keyEvent: KeyEvent): Boolean {
        when (view.id) {

            R.id.EnterNameMovie -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    binding?.resEnterNameMovie?.text = binding?.EnterNameMovie?.text
                    binding?.EnterNameMovie?.setText("")
                    return true
                }

            }

            R.id.EnterNewMovieGenre -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    binding?.resEnterMovieNameGenre?.text = binding?.EnterNewMovieGenre?.text
                    binding?.EnterNewMovieGenre?.setText("")
                    return true

                }

            }



                R.id.EnterMovieTime -> {
                    if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                        binding?.resEnterMovieTime?.text = binding?.EnterMovieTime?.text
                        binding?.EnterMovieTime?.setText("")
                        return true
                    }

                }


                    R.id.EnterMovieRating -> {
                    if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                        binding?.resEnterMovieRating?.text = binding?.EnterMovieRating?.text
                        binding?.EnterMovieRating?.setText("")
                        return true
                    }

            }
        }

        return false
    }

    override fun onClick(view: View) {

        when(view.id) {

            R.id.buttonAddHistorical -> {

                moviegenresViewModels?.startInsert(
                    binding?.buttonAddHistorical?.text?.toString()!!)

            }

            R.id.buttonAddFantasy -> {

                moviegenresViewModels?.startInsert(
                    binding?.buttonAddFantasy?.text?.toString()!!)

            }

            R.id.buttonAddAdventures -> {

                moviegenresViewModels?.startInsert(
                    binding?.buttonAddAdventures?.text?.toString()!!)

            }

            R.id.buttonAddMovie -> {

                movielistViewModels?.startInsert(
                    binding?.resEnterNameMovie?.text?.toString()!!,
                    binding?.resEnterMovieNameGenre?.text?.toString()!!,
                    binding?.resEnterMovieTime?.text?.toString()!!.toInt(),
                    binding?.resEnterMovieRating?.text?.toString()!!.toInt())

                clearResEnterProduct()

            }
        }

    }

    private fun clearResEnterProduct() {
        binding?.resEnterNameMovie?.setText("")
        binding?.resEnterMovieNameGenre?.setText("")
        binding?.resEnterMovieTime?.setText("")
        binding?.resEnterMovieRating?.setText("")


    }


}