package com.abdelrahman.rafaat.movies.ui.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdelrahman.rafaat.movies.adapter.FilterAdapter
import com.abdelrahman.rafaat.movies.adapter.FilterClickListener
import com.abdelrahman.rafaat.movies.databinding.FragmentFilterSheetBinding
import com.abdelrahman.rafaat.movies.model.Repository
import com.abdelrahman.rafaat.movies.model.SortBy
import com.abdelrahman.rafaat.movies.network.MovieClient
import com.abdelrahman.rafaat.movies.ui.search.viewmodel.SearchViewModel
import com.abdelrahman.rafaat.movies.ui.search.viewmodel.SearchViewModelFactory
import com.abdelrahman.rafaat.movies.utils.getListOfYears
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterSheetFragment : BottomSheetDialogFragment(), FilterClickListener {

    private lateinit var binding: FragmentFilterSheetBinding
    private lateinit var genreAdapter: FilterAdapter
    private lateinit var regionsAdapter: FilterAdapter

    private lateinit var genre: String
    private lateinit var sortBy: String
    private lateinit var region: String
    private lateinit var year: String
    private var page: Int = 1

    private val factory by lazy { SearchViewModelFactory(Repository.getInstance(MovieClient.getInstance())) }
    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), factory)[SearchViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initRecyclerView()
        observeViewModel()
    }

    private fun initUi() {
        binding.applyButton.setOnClickListener {
            viewModel.discoverMovie(genre, sortBy, region, year, page)
            this.dismiss()
        }

        binding.resetButton.setOnClickListener {
            this.dismiss()
        }
    }

    private fun initRecyclerView() {
        genreAdapter = FilterAdapter(this)
        binding.genresRecyclerView.apply {
            val customLayoutManager = LinearLayoutManager(requireContext())
            customLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = customLayoutManager
            adapter = genreAdapter
        }

        regionsAdapter = FilterAdapter(this)
        binding.regionRecyclerView.apply {
            val customLayoutManager = LinearLayoutManager(requireContext())
            customLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = customLayoutManager
            adapter = regionsAdapter
        }

        val sortByAdapter = FilterAdapter(this)
        binding.sortRecyclerView.apply {
            val customLayoutManager = LinearLayoutManager(requireContext())
            customLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = customLayoutManager
            adapter = sortByAdapter
            sortByAdapter.setDataSource(SortBy.values().toList())
        }

        val yearsAdapter = FilterAdapter(this)
        binding.yearRecyclerView.apply {
            val customLayoutManager = LinearLayoutManager(requireContext())
            customLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = customLayoutManager
            adapter = yearsAdapter
            yearsAdapter.setDataSource(getListOfYears(requireContext()))
        }
    }

    private fun observeViewModel() {
        viewModel.movieGenres.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                genreAdapter.setDataSource(it)
            } else {
                genreAdapter.setDataSource(emptyList())
            }

        }

        viewModel.regions.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                regionsAdapter.setDataSource(it)
            } else {
                regionsAdapter.setDataSource(emptyList())
            }

        }
    }


    override fun onGenreSelected(genre: String) {
        this.genre = genre
    }

    override fun onRegionSelected(region: String) {
        this.region = region
    }

    override fun onSortBySelected(sortBy: String) {
        this.sortBy = sortBy
    }

    override fun onYearSelected(year: String) {
        this.year = year
    }

}