package com.abdelrahman.rafaat.movies.ui.genreDetails.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdelrahman.rafaat.movies.databinding.FragmentCategoryDetailsBinding


class GenreDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCategoryDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("Test", "onCreateView: GenreDetailsFragment")
        binding = FragmentCategoryDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

}