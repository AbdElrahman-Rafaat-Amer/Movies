package com.abdelrahman.rafaat.movies.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdelrahman.rafaat.movies.R
import com.abdelrahman.rafaat.movies.databinding.SortItemBinding
import com.abdelrahman.rafaat.movies.model.Genre
import com.abdelrahman.rafaat.movies.model.Region
import com.abdelrahman.rafaat.movies.model.SortBy

class FilterAdapter(private var filterListener: FilterClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataSource: List<Any> = emptyList()
    private lateinit var context: Context
    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        val binding =
            SortItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SortByViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = dataSource[position]) {
            is Genre -> {
                (holder as SortByViewHolder).onBind(item)
            }

            is Region -> {
                (holder as SortByViewHolder).onBind(item)
            }

            is String -> {
                (holder as SortByViewHolder).onBind(item)
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

    inner class SortByViewHolder(private var binding: SortItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(region: Region) {
            binding.ascendingImage.visibility = View.GONE
            binding.descendingImage.visibility = View.GONE
            binding.sortByValue.text = region.english_name
            binding.sortByValue.setOnClickListener {
                notifyItemChanged(selectedPosition)
                selectedPosition = adapterPosition
                notifyItemChanged(adapterPosition)
            }

            if (selectedPosition == adapterPosition) {
                updateSelectedItem()
                filterListener.onRegionSelected(region.english_name)
            } else {
                updateUnselectedItem()
            }
        }

        fun onBind(genre: Genre) {
            binding.ascendingImage.visibility = View.GONE
            binding.descendingImage.visibility = View.GONE
            binding.sortByValue.text = genre.name
            binding.sortByValue.setOnClickListener {
                notifyItemChanged(selectedPosition)
                selectedPosition = adapterPosition
                notifyItemChanged(adapterPosition)
            }

            if (selectedPosition == adapterPosition) {
                updateSelectedItem()
                filterListener.onGenreSelected(genre.name)
            } else {
                updateUnselectedItem()
            }
        }

        fun onBind(year: String) {
            binding.ascendingImage.visibility = View.GONE
            binding.descendingImage.visibility = View.GONE
            binding.sortByValue.text = year
            binding.sortByValue.setOnClickListener {
                updateUnselectedItem()
                notifyItemChanged(selectedPosition)
                selectedPosition = adapterPosition
                notifyItemChanged(adapterPosition)
            }

            if (selectedPosition == adapterPosition) {
                updateSelectedItem()
                filterListener.onYearSelected(year)
            } else {
                updateUnselectedItem()
            }
        }

        fun onBind(sortBy: SortBy) {
            binding.sortByValue.text = context.getString(sortBy.value!!)
            binding.rooLayout.setOnClickListener {
                sortBy.isDescending = !sortBy.isDescending!!
                notifyItemChanged(selectedPosition)
                selectedPosition = adapterPosition
                notifyItemChanged(adapterPosition)
            }

            if (selectedPosition == adapterPosition) {
                updateSelectedItem()
                if (sortBy.isDescending == true) {
                    binding.ascendingImage.setImageResource(R.drawable.unselected_arrow_upward)
                    binding.descendingImage.setImageResource(R.drawable.selected_arrow_downward)
                    filterListener.onSortBySelected(sortBy.descending!!)
                } else {
                    binding.ascendingImage.setImageResource(R.drawable.selected_arrow_upward)
                    binding.descendingImage.setImageResource(R.drawable.unselected_arrow_downward)
                    filterListener.onSortBySelected(sortBy.ascending!!)
                }
            } else {
                updateUnselectedItem()
            }
        }

        private fun updateSelectedItem() {
            binding.sortByValue.setTextColor(Color.WHITE)
            binding.root.setBackgroundResource(R.drawable.genre_item_background)
        }

        private fun updateUnselectedItem() {
            binding.sortByValue.setTextColor(context.getColor(R.color.main_color))
            binding.root.setBackgroundResource(R.drawable.item_background_white)
        }


    }

}