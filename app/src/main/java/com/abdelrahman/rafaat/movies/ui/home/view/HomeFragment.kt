package com.abdelrahman.rafaat.movies.ui.home.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdelrahman.rafaat.movies.R
import com.abdelrahman.rafaat.movies.base.BaseFragment
import com.abdelrahman.rafaat.movies.databinding.FragmentHomeBinding
import com.abdelrahman.rafaat.movies.model.Repository
import com.abdelrahman.rafaat.movies.adapter.*
import com.abdelrahman.rafaat.movies.ui.home.viewmodel.HomeViewModel
import com.abdelrahman.rafaat.movies.ui.home.viewmodel.HomeViewModelFactory
import com.abdelrahman.rafaat.movies.network.MovieClient
import com.abdelrahman.rafaat.movies.utils.connectInternet

private var TAG = HomeFragment::class.java.name

class HomeFragment : BaseFragment(), MovieClickListener, GenreClickListener{

    private lateinit var binding: FragmentHomeBinding
    private lateinit var trendingAdapter: TrendingAdapter
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var topRatedAdapter: MovieAdapter
    private lateinit var popularAdapter: MovieAdapter
    private lateinit var upComingAdapter: MovieAdapter
    private val factory by lazy { HomeViewModelFactory(Repository.getInstance(MovieClient.getInstance())) }
    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), factory)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initRecyclerView()
    }

    private fun callViewModel() {
        viewModel.getTrendingMovie("movie", "week")
        viewModel.getMovieGenres()
        viewModel.getPopularMovies()
        viewModel.getTopRatedMovies()
        viewModel.getUpcomingMovies()
    }

    private fun initUi() {
        trendingAdapter = TrendingAdapter(this)
        binding.trendingMovies.adapter = trendingAdapter

        genreAdapter = GenreAdapter(this)
        topRatedAdapter = MovieAdapter(this)
        popularAdapter = MovieAdapter(this)
        upComingAdapter = MovieAdapter(this)

        binding.seeAllPopular.setOnClickListener {
            Toast.makeText(requireContext(), "seeAllPopular", Toast.LENGTH_SHORT)
                .show()
            Navigation.findNavController(requireView()).navigate(R.id.navigation_categoryDetails)
        }

        binding.seeAllTopRated.setOnClickListener {
            Toast.makeText(requireContext(), "seeAllTopRated", Toast.LENGTH_SHORT)
                .show()
            Navigation.findNavController(requireView()).navigate(R.id.navigation_categoryDetails)
        }

        binding.seeAllUpComing.setOnClickListener {
            Toast.makeText(requireContext(), "seeAllUpComing", Toast.LENGTH_SHORT)
                .show()
            Navigation.findNavController(requireView()).navigate(R.id.navigation_categoryDetails)
        }

        binding.noConnectionView.enableConnection.setOnClickListener {
            connectInternet(requireContext())
        }
    }

    private fun initRecyclerView() {
        binding.genresRecyclerView.apply {
            val customLayoutManager = LinearLayoutManager(requireContext())
            customLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = customLayoutManager
            adapter = genreAdapter
        }

        binding.topRatedMoviesRecyclerView.apply {
            val customLayoutManager = LinearLayoutManager(requireContext())
            customLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = customLayoutManager
            adapter = topRatedAdapter
        }

        binding.popularMoviesRecyclerView.apply {
            val customLayoutManager = LinearLayoutManager(requireContext())
            customLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = customLayoutManager
            adapter = popularAdapter
        }

        binding.upComingMoviesRecyclerView.apply {
            val customLayoutManager = LinearLayoutManager(requireContext())
            customLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = customLayoutManager
            adapter = upComingAdapter
        }

    }

    private fun observeViewModel() {
        viewModel.trendingMovies.observe(viewLifecycleOwner) {
            if (it.results.isNotEmpty()) {
                trendingAdapter.setList(it.results)
                binding.dataContainer.visibility = View.VISIBLE
            } else {
                trendingAdapter.setList(emptyList())
            }
            stopAnimation()
        }

        viewModel.movieGenres.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                genreAdapter.setList(it)
                binding.dataContainer.visibility = View.VISIBLE
            } else {
                genreAdapter.setList(emptyList())
            }
            stopAnimation()
        }

        viewModel.topRatedMovies.observe(viewLifecycleOwner) {
            if (it.results.isNotEmpty()) {
                topRatedAdapter.setList(it.results)
                binding.dataContainer.visibility = View.VISIBLE
            } else {
                topRatedAdapter.setList(emptyList())
            }
            stopAnimation()
        }

        viewModel.popularMovies.observe(viewLifecycleOwner) {
            if (it.results.isNotEmpty()) {
                popularAdapter.setList(it.results)
                binding.dataContainer.visibility = View.VISIBLE
            } else {
                popularAdapter.setList(emptyList())
            }
            stopAnimation()
        }

        viewModel.upComingMovies.observe(viewLifecycleOwner) {
            if (it.results.isNotEmpty()) {
                upComingAdapter.setList(it.results)
                binding.dataContainer.visibility = View.VISIBLE
            } else {
                upComingAdapter.setList(emptyList())
            }
            stopAnimation()
        }

    }

    private fun stopAnimation() {
        binding.shimmerAnimationLayout.root.visibility = View.GONE
        binding.shimmerAnimationLayout.shimmerFrameLayout.stopShimmer()
    }

    override fun onMovieClick(movieId: Int) {
        Navigation.findNavController(requireView()).navigate(R.id.navigation_movieDetails)
    }

    override fun onImageLongClick(imageLink: String, movieName: String) {
        downloadImage(imageLink, movieName)
    }

    override fun onNameLongClick(movieName: String) {
        val data = ClipData.newPlainText("Movie_Name", movieName)
        val clipboardManager =
            requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboardManager.setPrimaryClip(data)
        Toast.makeText(requireContext(), getString(R.string.copy_success), Toast.LENGTH_SHORT)
            .show()
    }

    override fun onGenreClick(genreId: Int) {
        Navigation.findNavController(requireView()).navigate(R.id.navigation_genreDetails)
    }



    override fun connected() {
        super.connected()
        Log.d(TAG, "connected: ")
        binding.shimmerAnimationLayout.root.visibility = View.VISIBLE
        binding.shimmerAnimationLayout.shimmerFrameLayout.startShimmer()
        binding.noConnectionView.root.visibility = View.GONE
        callViewModel()
        observeViewModel()
    }

    override fun disconnected() {
        super.disconnected()
        Log.d(TAG, "disconnected: ")
        binding.noConnectionView.root.visibility = View.VISIBLE
        binding.dataContainer.visibility = View.GONE
        stopAnimation()
    }

}