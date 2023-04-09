package com.abdelrahman.rafaat.movies.ui.authentication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdelrahman.rafaat.movies.R
import com.abdelrahman.rafaat.movies.databinding.FragmentVerifyCodeBinding

class VerifyCodeFragment : Fragment() {

    private lateinit var binding: FragmentVerifyCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVerifyCodeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}