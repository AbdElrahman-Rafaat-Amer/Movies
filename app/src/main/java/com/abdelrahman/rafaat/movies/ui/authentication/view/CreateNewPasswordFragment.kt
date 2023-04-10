package com.abdelrahman.rafaat.movies.ui.authentication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.abdelrahman.rafaat.movies.R
import com.abdelrahman.rafaat.movies.databinding.FragmentCreateNewPasswordBinding

class CreateNewPasswordFragment : Fragment() {

    private lateinit var binding: FragmentCreateNewPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateNewPasswordBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUI()
    }

    private fun setUpUI() {
        binding.continueButton.setOnClickListener {
            findNavController().navigate(R.id.action_createNewPasswordFragment_to_verifyCodeFragment)
        }
    }
}