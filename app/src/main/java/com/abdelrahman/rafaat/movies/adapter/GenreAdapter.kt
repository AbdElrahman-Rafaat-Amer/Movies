package com.abdelrahman.rafaat.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdelrahman.rafaat.movies.databinding.GenreItemBinding
import com.abdelrahman.rafaat.movies.model.Genre

class GenreAdapter(private var itemClickListener: GenreClickListener) :
    RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    private var genres: List<Genre> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = genres[position]
        holder.onBind(genre)

    }

    override fun getItemCount(): Int {
        return genres.size
    }

    fun setList(genres: List<Genre>) {
        this.genres = genres
        notifyDataSetChanged()
    }

    inner class ViewHolder(private var binding: GenreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(genre: Genre) {
            binding.genreName.text = genre.name
            binding.genreName.setOnClickListener {
                itemClickListener.onGenreClick(genre.id)
            }
        }

    }


}