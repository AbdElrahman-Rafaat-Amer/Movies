package com.abdelrahman.rafaat.movies.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abdelrahman.rafaat.movies.R
import com.abdelrahman.rafaat.movies.base.BaseFragment
import com.abdelrahman.rafaat.movies.ui.home.view.HomeFragment

private var TAG = ProfileFragment::class.java.name

class ProfileFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
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