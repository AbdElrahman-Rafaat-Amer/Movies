package com.abdelrahman.rafaat.movies.ui.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.abdelrahman.rafaat.movies.databinding.ActivityAuthenticationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private const val TAG = "AuthenticationActivity"
class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthenticationBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        checkIsLogged()
    }

    private fun checkIsLogged() {
        val currentUser = auth.currentUser
        Log.i(TAG, "checkIsLogged: $currentUser")
    }
}