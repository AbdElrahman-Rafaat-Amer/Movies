package com.abdelrahman.rafaat.movies.ui.home.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdelrahman.rafaat.movies.R
import com.abdelrahman.rafaat.movies.databinding.FragmentHomeBinding
import com.abdelrahman.rafaat.movies.model.Repository
import com.abdelrahman.rafaat.movies.ui.home.adapter.*
import com.abdelrahman.rafaat.movies.ui.home.viewmodel.HomeViewModel
import com.abdelrahman.rafaat.movies.ui.home.viewmodel.HomeViewModelFactory
import com.abdelrahman.rafaat.movies.ui.network.MovieClient
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class HomeFragment : Fragment(), MovieClickListener, GenreClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var trendingAdapter: TrendingAdapter
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var topRatedAdapter: MovieAdapter
    private lateinit var popularAdapter: MovieAdapter
    private lateinit var upComingAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
        initUi()
        initRecyclerView()
        initViewModel()
        observeViewModel()
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
        }

        binding.seeAllTopRated.setOnClickListener {
            Toast.makeText(requireContext(), "seeAllTopRated", Toast.LENGTH_SHORT)
                .show()
        }

        binding.seeAllUpComing.setOnClickListener {
            Toast.makeText(requireContext(), "seeAllUpComing", Toast.LENGTH_SHORT)
                .show()
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


    private fun initViewModel() {
        val viewModelFactory = HomeViewModelFactory(
            Repository.getInstance(MovieClient.getInstance())
        )

        viewModel = ViewModelProvider(
            requireActivity(),
            viewModelFactory
        )[HomeViewModel::class.java]

        viewModel.getTrendingMovie("movie", "week")
        viewModel.getMovieGenres()
        viewModel.getPopularMovies()
        viewModel.getTopRatedMovies()
        viewModel.getUpcomingMovies()
    }

    private fun observeViewModel() {
        viewModel.trendingMovies.observe(viewLifecycleOwner) {
            Log.i("TAGTest", "observeViewModel: size------------->" + it.results.size)
            if (it.results.isNotEmpty()) {
                trendingAdapter.setList(it.results)
            } else {
                trendingAdapter.setList(emptyList())
            }

        }

        viewModel.movieGenres.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                genreAdapter.setList(it)
            } else {
                genreAdapter.setList(emptyList())
            }
        }

        viewModel.topRatedMovies.observe(viewLifecycleOwner) {
            if (it.results.isNotEmpty()) {
                topRatedAdapter.setList(it.results)
            } else {
                topRatedAdapter.setList(emptyList())
            }
        }

        viewModel.popularMovies.observe(viewLifecycleOwner) {
            Log.i("TAGTest", "observeViewModel: size------------->" + it.results.size)
            if (it.results.isNotEmpty()) {
                popularAdapter.setList(it.results)
            } else {
                popularAdapter.setList(emptyList())
            }
        }


        viewModel.upComingMovies.observe(viewLifecycleOwner) {
            Log.i("TAGTest", "observeViewModel: size------------->" + it.results.size)
            if (it.results.isNotEmpty()) {
                upComingAdapter.setList(it.results)
            } else {
                upComingAdapter.setList(emptyList())
            }
        }

    }

    override fun onMovieClick(movieId: Int) {
        Log.i("TestClick", "onMovieClick: movieId------------> $movieId")
    }

    override fun onImageLongClick(movieImage: Bitmap, movieName: String) {
        prepareFile(movieImage, movieName)

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
        Log.i("TestClick", "onGenreClick: genreId------------> $genreId")
    }

    private fun prepareFile(bitmap: Bitmap, movieName: String) {
        val file: String =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()

        val dir = File("$file/Filmk")
        dir.mkdirs()
        val outFile = File(dir, "$movieName.png")
        outFile.exists()
        try {
            saveImage(outFile, bitmap)
            Log.i("SaveImage", "prepareFile: saveImage success")
            Toast.makeText(requireContext(), "Saved Success", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Log.i("SaveImage", "Exception: IOException" + e.message)
            Toast.makeText(requireContext(), "Saved failed: " + e.message, Toast.LENGTH_SHORT)
                .show()
        }
    }

    @Throws(IOException::class)
    private fun saveImage(outFile: File, bitmap: Bitmap) {
        val outputStream = FileOutputStream(outFile)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        galleryAddPic(outFile.absolutePath)
        outputStream.flush()
        outputStream.close()
    }

    private fun galleryAddPic(pathOfSavedImage: String) {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val f = File(pathOfSavedImage)
        val contentUri = Uri.fromFile(f)
        mediaScanIntent.data = contentUri
        requireContext().sendBroadcast(mediaScanIntent)
    }
}