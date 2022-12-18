package com.abdelrahman.rafaat.movies.ui.search.view

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.abdelrahman.rafaat.movies.R
import com.abdelrahman.rafaat.movies.databinding.FragmentSearchBinding
import com.abdelrahman.rafaat.movies.model.Repository
import com.abdelrahman.rafaat.movies.adapter.MovieAdapter
import com.abdelrahman.rafaat.movies.adapter.MovieClickListener
import com.abdelrahman.rafaat.movies.network.MovieClient
import com.abdelrahman.rafaat.movies.ui.search.viewmodel.SearchViewModel
import com.abdelrahman.rafaat.movies.ui.search.viewmodel.SearchViewModelFactory
import com.abdelrahman.rafaat.movies.utils.connectInternet
import kotlinx.coroutines.Job
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdelrahman.rafaat.movies.adapter.FilterAdapter
import com.abdelrahman.rafaat.movies.adapter.FilterClickListener
import com.abdelrahman.rafaat.movies.base.BaseFragment
import com.abdelrahman.rafaat.movies.model.SortBy
import com.abdelrahman.rafaat.movies.utils.getListOfYears
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private var TAG = SearchFragment::class.java.name

class SearchFragment : BaseFragment(), MovieClickListener, FilterClickListener {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchAdapter: MovieAdapter
  //  private lateinit var genreAdapter: FilterAdapter
   // private lateinit var regionsAdapter: FilterAdapter
    private val factory by lazy { SearchViewModelFactory(Repository.getInstance(MovieClient.getInstance())) }
    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), factory)[SearchViewModel::class.java]
    }

    //   private lateinit var bottomSheetDialog: BottomSheetDialog
    private val bottomSheet = FilterSheetFragment()
    private lateinit var genre: String
    private lateinit var sortBy: String
    private lateinit var region: String
    private lateinit var year: String
    private var page: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //   bottomSheetDialog = BottomSheetDialog(requireContext())
        initUi()
        initRecyclerView()
        handleSearchView()

    }

    private fun initUi() {
        binding.noConnectionView.enableConnection.setOnClickListener {
            connectInternet(requireContext())
        }

        binding.searchFilter.setOnClickListener {
            bottomSheet.show(
                requireActivity().supportFragmentManager,
                bottomSheet.tag
            )
           // binding.searchFilterLayout.root.visibility = View.VISIBLE
            //  showBottomSheet()
        }

        /*binding.searchFilterLayout.applyButton.setOnClickListener {
            viewModel.discoverMovie(genre, sortBy, region, year, page)
            //    bottomSheetDialog.dismiss()
        }

        binding.searchFilterLayout.resetButton.setOnClickListener {
            //    bottomSheetDialog.dismiss()
            bottomSheet.dismiss()
        }*/
    }

    /* private fun showBottomSheet() {
         bottomSheetDialog.setContentView(
             layoutInflater.inflate(
                 R.layout.fragment_filter_sheet,
                 null
             )
         )


         bottomSheetDialog.setCancelable(false)
         bottomSheetDialog.show()
     }*/

    private fun initRecyclerView() {
        searchAdapter = MovieAdapter(this)
        binding.searchRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = searchAdapter
        }

        /*genreAdapter = FilterAdapter(this)
        binding.searchFilterLayout.genresRecyclerView.apply {
            val customLayoutManager = LinearLayoutManager(requireContext())
            customLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = customLayoutManager
            adapter = genreAdapter
        }

        regionsAdapter = FilterAdapter(this)
        binding.searchFilterLayout.regionRecyclerView.apply {
            val customLayoutManager = LinearLayoutManager(requireContext())
            customLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = customLayoutManager
            adapter = regionsAdapter
        }

        val sortByAdapter = FilterAdapter(this)
        binding.searchFilterLayout.sortRecyclerView.apply {
            val customLayoutManager = LinearLayoutManager(requireContext())
            customLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = customLayoutManager
            adapter = sortByAdapter
            sortByAdapter.setDataSource(SortBy.values().toList())
        }

        val yearsAdapter = FilterAdapter(this)
        binding.searchFilterLayout.yearRecyclerView.apply {
            val customLayoutManager = LinearLayoutManager(requireContext())
            customLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = customLayoutManager
            adapter = yearsAdapter
            yearsAdapter.setDataSource(getListOfYears(requireContext()))
        }*/
    }

    private fun handleSearchView() {
        binding.searchView.apply {
            var job: Job? = null

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    job?.cancel()
                    job = MainScope().launch {
                        delay(500L)
                        if (newText!!.isNotEmpty()) {
                            viewModel.searchMovie(newText, 1)
                        }
                    }
                    return false
                }

            })
        }
    }

    private fun observeViewModel() {
        viewModel.movies.observe(viewLifecycleOwner) {
            if (it.results.isNotEmpty()) {
                searchAdapter.setList(it.results)
                binding.noDataView.root.visibility = View.GONE
            } else {
                searchAdapter.setList(emptyList())
                binding.noDataView.root.visibility = View.VISIBLE
            }

        }

  /*      viewModel.movieGenres.observe(viewLifecycleOwner) {
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

        }*/
    }

    override fun onMovieClick(movieId: Int) {
        Navigation.findNavController(requireView()).navigate(R.id.navigation_movieDetails)
    }

    override fun onImageLongClick(imageLink: String, movieName: String) = Unit

    override fun onNameLongClick(movieName: String) = Unit

    override fun connected() {
        super.connected()
        Log.i(TAG, "connected: ")
        binding.noConnectionView.root.visibility = View.GONE
        changeViewVisibility(View.VISIBLE)
        observeViewModel()
    }

    override fun disconnected() {
        super.disconnected()
        Log.i(TAG, "disconnected: ")
        binding.noConnectionView.root.visibility = View.VISIBLE
        changeViewVisibility(View.GONE)
    }

    private fun changeViewVisibility(visibility: Int) {
        binding.searchFilter.visibility = visibility
        binding.searchView.visibility = visibility
        binding.searchRecyclerView.visibility = visibility
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