package com.abdelrahman.rafaat.movies.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdelrahman.rafaat.movies.R
import com.abdelrahman.rafaat.movies.databinding.GenreItemBinding
import com.abdelrahman.rafaat.movies.databinding.SortItemBinding
import com.abdelrahman.rafaat.movies.model.Genre
import com.abdelrahman.rafaat.movies.model.Region
import com.abdelrahman.rafaat.movies.model.SortBy


class FilterAdapter(private var filterListener: FilterClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataSource: List<Any> = emptyList()
    private lateinit var context: Context

    override fun getItemViewType(position: Int): Int {
        return when (dataSource[position]) {
            is SortBy -> {
                0
            }
            else -> {
                1
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return when (viewType) {
            0 -> {
                val binding =
                    SortItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                SortByViewHolder(binding)
            }
            else -> {
                val binding =
                    GenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                RegionViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = dataSource[position]) {
            is Genre -> {
                (holder as RegionViewHolder).onBind(item)
            }

            is Region -> {
                (holder as RegionViewHolder).onBind(item)
            }

            is String -> {
                (holder as RegionViewHolder).onBind(item)
            }

            is SortBy -> {
                (holder as SortByViewHolder).onBind(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    fun setDataSource(dataSource: List<Any>) {
        this.dataSource = dataSource
        notifyDataSetChanged()
    }


    inner class RegionViewHolder(private var binding: GenreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(region: Region) {
            binding.genreName.text = region.english_name
            binding.genreName.setOnClickListener {
                binding.genreName.setTextColor(Color.WHITE)
                binding.genreName.setBackgroundResource(R.drawable.genre_item_background_red)
                filterListener.onRegionSelected(region.english_name)
            }
        }

        fun onBind(genre: Genre) {
            binding.genreName.text = genre.name
            binding.genreName.setOnClickListener {
                binding.genreName.setTextColor(Color.WHITE)
                binding.genreName.setBackgroundResource(R.drawable.genre_item_background_red)
                filterListener.onGenreSelected(genre.name)
            }
        }

        fun onBind(year: String) {
            binding.genreName.text = year
            binding.genreName.setOnClickListener {
                binding.genreName.setTextColor(Color.WHITE)
                binding.genreName.setBackgroundResource(R.drawable.genre_item_background_red)
                filterListener.onYearSelected(year)
            }
        }
    }

    inner class SortByViewHolder(private var binding: SortItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(sortBy: SortBy) {
            var numberOfClicks = 0
            binding.sortByValue.text = context.getString(sortBy.value!!)
            binding.root.setOnClickListener {
                binding.sortByValue.setTextColor(Color.WHITE)
                binding.root.setBackgroundResource(R.drawable.genre_item_background_red)
                numberOfClicks++
                if (numberOfClicks % 2 == 0) {
                    binding.ascendingImage.setImageResource(R.drawable.unselected_arrow_upward)
                    binding.descendingImage.setImageResource(R.drawable.selected_arrow_downward)
                    filterListener.onSortBySelected(sortBy.descending!!)

                } else {
                    binding.ascendingImage.setImageResource(R.drawable.selected_arrow_upward)
                    binding.descendingImage.setImageResource(R.drawable.unselected_arrow_downward)
                    filterListener.onSortBySelected(sortBy.ascending!!)
                }

            }
        }

    }

}