package com.abdelrahman.rafaat.movies.ui.moviedetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdelrahman.rafaat.movies.base.BaseFragment
import com.abdelrahman.rafaat.movies.databinding.FragmentMovieDetailsBinding

private var TAG = MovieDetailsFragment::class.java.name


class MovieDetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun connected() {
        super.connected()
        Log.i(TAG, "connected: ")
    }

    override fun disconnected() {
        super.disconnected()
        Log.i(TAG, "disconnected: ")
    }
}