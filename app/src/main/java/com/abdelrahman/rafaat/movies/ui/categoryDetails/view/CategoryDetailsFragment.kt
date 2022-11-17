package com.abdelrahman.rafaat.movies.ui.categoryDetails.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdelrahman.rafaat.movies.R
import com.abdelrahman.rafaat.movies.ui.categoryDetails.viewmodel.CategoryDetailsViewModel

class CategoryDetailsFragment : Fragment() {

    private lateinit var viewModel: CategoryDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_details, container, false)
    }

}