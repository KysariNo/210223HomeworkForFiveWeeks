package com.example.a210223homeworkforfiveweeks.tabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a210223homeworkforfiveweeks.databinding.GenresItemBinding
import com.example.a210223homeworkforfiveweeks.models.MovieGenresModel

class MovieGenresAdapter (private val deleteMovieGenres:(MovieGenresModel)->Unit,
                          private val editMovieGenres:(MovieGenresModel)->Unit): RecyclerView.Adapter<MovieGenresAdapter.MovieGenresHolder>() {

    private val moviegenresList = ArrayList<MovieGenresModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGenresHolder {

        val binding : GenresItemBinding = GenresItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieGenresHolder(binding)
    }

    override fun getItemCount(): Int {
        return moviegenresList.size
    }

    override fun onBindViewHolder(holder: MovieGenresHolder, position: Int) {
        holder.bind(moviegenresList[position], deleteMovieGenres, editMovieGenres)
    }

    fun setList(moviegenres: List<MovieGenresModel>) {
        moviegenresList.clear()
        moviegenresList.addAll(moviegenres)

    }


    class MovieGenresHolder(val binding: GenresItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            moviegenres: MovieGenresModel,
            deleteMovieGenres:(MovieGenresModel)->Unit,
            editMovieGenres:(MovieGenresModel)->Unit
        ) {

            binding.idMovieGenres.text = moviegenres.id.toString()
            binding.nameMovieGenres.text = moviegenres.name

            binding.editMovieGenres.setOnClickListener(View.OnClickListener {
                editMovieGenres(moviegenres)

            })

            binding.deleteMovieGenres.setOnClickListener(View.OnClickListener {
                deleteMovieGenres(moviegenres)
            })
        }




    }

}
