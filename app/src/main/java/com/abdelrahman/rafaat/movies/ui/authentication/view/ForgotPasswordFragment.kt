package com.abdelrahman.rafaat.movies.ui.authentication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdelrahman.rafaat.movies.R
import com.abdelrahman.rafaat.movies.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgotPasswordBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUI()
    }

    private fun setUpUI(){

        binding.smsCardView.setOnClickListener {
            binding.smsCardView.setBackgroundResource(R.drawable.checkbox_background)
            binding.emailBackground.setBackgroundResource(R.drawable.background_social)
        }

        binding.emailBackground.setOnClickListener {
            binding.emailBackground.setBackgroundResource(R.drawable.checkbox_background)
            binding.smsCardView.setBackgroundResource(R.drawable.background_social)
        }
    }
}