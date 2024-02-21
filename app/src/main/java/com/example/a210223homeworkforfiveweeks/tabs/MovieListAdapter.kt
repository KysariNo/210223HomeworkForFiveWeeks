package com.example.a210223homeworkforfiveweeks.tabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a210223homeworkforfiveweeks.databinding.MovieItemBinding
import com.example.a210223homeworkforfiveweeks.models.MovieListModel

class MovieListAdapter (private val deleteMovieList:(MovieListModel)->Unit,
                        private val editMovieList:(MovieListModel)->Unit) : RecyclerView.Adapter<MovieListAdapter.MovieListHolder>() {

    private val movielistList = ArrayList<MovieListModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListHolder {

        val binding : MovieItemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListHolder(binding)
    }

    override fun getItemCount(): Int {
        return movielistList.size
    }

    override fun onBindViewHolder(holder: MovieListHolder, position: Int) {
        holder.bind(movielistList[position], deleteMovieList, editMovieList)
    }

    fun setList(products: List<MovieListModel>) {
        movielistList.clear()
        movielistList.addAll(products)

    }


    class MovieListHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            movielistModel: MovieListModel,
            deleteMovieList: (MovieListModel) -> Unit,
            editMovieList: (MovieListModel) -> Unit

        ) {

            binding.idMovie.text = movielistModel.id.toString()
            binding.MovieName.text = movielistModel.name
            binding.MovieGenre.text = movielistModel.genres
            binding.MovieTime.text = movielistModel.time.toString()
            binding.MovieRating.text = movielistModel.rating.toString()


            binding.editMovie.setOnClickListener(View.OnClickListener {
                editMovieList(movielistModel)
            })

            binding.deleteMovie.setOnClickListener(View.OnClickListener {
                deleteMovieList(movielistModel)
            })

        }




    }

}
