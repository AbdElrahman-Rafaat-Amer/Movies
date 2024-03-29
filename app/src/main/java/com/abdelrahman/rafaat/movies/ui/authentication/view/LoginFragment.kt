package com.abdelrahman.rafaat.movies.ui.authentication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abdelrahman.rafaat.movies.R
import com.abdelrahman.rafaat.movies.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()

    }

    private fun setupUI() {
        binding.rememberMeCheckBox.setButtonDrawable(R.drawable.checkbox_background)

        binding.signInButton.setOnClickListener {
            binding.signInButton.startAnimation()
        }
    }
}