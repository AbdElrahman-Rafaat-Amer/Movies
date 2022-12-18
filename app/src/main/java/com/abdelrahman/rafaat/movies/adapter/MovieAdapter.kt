package com.abdelrahman.rafaat.movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdelrahman.rafaat.movies.R
import com.abdelrahman.rafaat.movies.databinding.MovieItemBinding
import com.abdelrahman.rafaat.movies.model.Result
import com.abdelrahman.rafaat.movies.network.RetrofitHelper
import com.bumptech.glide.Glide

class MovieAdapter(private var itemClickListener: MovieClickListener) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movies: List<Result> = emptyList()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = MovieItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.onBind(movie, context)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setList(movies: List<Result>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(movie: Result, context: Context) {
            Glide.with(context).asBitmap()
                .load(RetrofitHelper.BASE_IMAGE_W500_URL_API + movie.posterPath).override(200, 270)
                .placeholder(R.drawable.clapperboard).into(binding.moviePoster)

            binding.movieRate.text = movie.voteAverage.toString()
            binding.movieTitle.text = movie.originalTitle

            binding.moviePoster.setOnLongClickListener {
                itemClickListener.onImageLongClick(
                    imageLink = RetrofitHelper.BASE_IMAGE_W500_URL_API + movie.posterPath,
                    movieName = binding.movieTitle.text.toString()
                )
                true
            }

            binding.movieTitle.setOnLongClickListener {
                itemClickListener.onNameLongClick(binding.movieTitle.text.toString())
                true
            }

            binding.moviePoster.setOnClickListener {
                itemClickListener.onMovieClick(movie.id)
            }
        }

    }


}