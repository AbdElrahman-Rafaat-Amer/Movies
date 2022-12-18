package com.abdelrahman.rafaat.movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdelrahman.rafaat.movies.R
import com.abdelrahman.rafaat.movies.databinding.TrendingMovieItemBinding
import com.abdelrahman.rafaat.movies.model.Result
import com.abdelrahman.rafaat.movies.network.RetrofitHelper
import com.bumptech.glide.Glide


class TrendingAdapter(private var itemClickListener: MovieClickListener) :
    RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {

    private var movies: List<Result> = emptyList()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding =
            TrendingMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.onBind(movie, context)
        holder.itemView.setOnClickListener {
            itemClickListener.onMovieClick(movie.id)
        }

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setList(movies: List<Result>) {
        this.movies = movies
        notifyDataSetChanged()

    }

    inner class ViewHolder(private val binding: TrendingMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(movie: Result, context: Context) {
            Glide.with(context)
                .load(RetrofitHelper.BASE_IMAGE_W500_URL_API + movie.posterPath)
                .placeholder(R.drawable.clapperboard).into(binding.posterImageView)

            Glide.with(context)
                .load(RetrofitHelper.BASE_IMAGE_URL_ORIGINAL_API + movie.backdropPath)
                .placeholder(R.drawable.clapperboard).into(binding.originalImageView)

            binding.movieTitle.text = movie.originalTitle

            binding.originalImageView.setOnLongClickListener {
                itemClickListener.onImageLongClick(
                    imageLink = RetrofitHelper.BASE_IMAGE_URL_ORIGINAL_API + movie.backdropPath,
                    movieName = binding.movieTitle.text.toString()
                )
                true
            }

            binding.movieTitle.setOnLongClickListener {
                itemClickListener.onNameLongClick(binding.movieTitle.text.toString())
                true
            }

            binding.originalImageView.setOnClickListener {
                itemClickListener.onMovieClick(movie.id)
            }

        }

    }


}