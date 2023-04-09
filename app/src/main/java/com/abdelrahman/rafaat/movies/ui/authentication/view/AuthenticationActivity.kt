package com.abdelrahman.rafaat.movies.ui.authentication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abdelrahman.rafaat.movies.databinding.ActivityAuthenticationBinding

private const val TAG = "AuthenticationActivity"

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}