package com.abdelrahman.rafaat.movies.ui.search.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.abdelrahman.rafaat.movies.R
import com.abdelrahman.rafaat.movies.databinding.FragmentSearchBinding
import com.abdelrahman.rafaat.movies.model.Repository
import com.abdelrahman.rafaat.movies.ui.home.adapter.MovieAdapter
import com.abdelrahman.rafaat.movies.ui.home.adapter.MovieClickListener
import com.abdelrahman.rafaat.movies.ui.network.MovieClient
import com.abdelrahman.rafaat.movies.ui.search.viewmodel.SearchViewModel
import com.abdelrahman.rafaat.movies.ui.search.viewmodel.SearchViewModelFactory
import com.abdelrahman.rafaat.movies.utils.ConnectionLiveData
import com.abdelrahman.rafaat.movies.utils.connectInternet
import kotlinx.coroutines.Job
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchFragment : Fragment(), MovieClickListener {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchAdapter: MovieAdapter
    private val factory by lazy { SearchViewModelFactory(Repository.getInstance(MovieClient.getInstance())) }
    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), factory)[SearchViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkConnection()
        initUi()
        initRecyclerView()
        handleSearchView()
    }

    private fun checkConnection() {
        ConnectionLiveData.getInstance(requireContext()).observe(viewLifecycleOwner) {
            if (it) {
                binding.noConnectionView.root.visibility = View.GONE
                binding.searchFilter.visibility = View.VISIBLE
                binding.searchView.visibility = View.VISIBLE
                binding.searchRecyclerView.visibility = View.VISIBLE
                observeViewModel()
            } else {
                binding.noConnectionView.root.visibility = View.VISIBLE
                binding.searchFilter.visibility = View.GONE
                binding.searchView.visibility = View.GONE
                binding.searchRecyclerView.visibility = View.GONE
            }
        }
    }

    private fun initUi() {

        binding.noConnectionView.enableConnection.setOnClickListener {
            connectInternet(requireContext())
        }
    }

    private fun initRecyclerView() {
        searchAdapter = MovieAdapter(this)
        binding.searchRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = searchAdapter
        }
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
    }


    override fun onMovieClick(movieId: Int) {
        Navigation.findNavController(requireView()).navigate(R.id.navigation_movieDetails)
    }

    override fun onImageLongClick(imageLink: String, movieName: String) = Unit

    override fun onNameLongClick(movieName: String) = Unit


}