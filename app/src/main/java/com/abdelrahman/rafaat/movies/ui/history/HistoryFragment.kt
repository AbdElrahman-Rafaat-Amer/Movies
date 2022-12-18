package com.abdelrahman.rafaat.movies.ui.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdelrahman.rafaat.movies.base.BaseFragment
import com.abdelrahman.rafaat.movies.databinding.FragmentHistoryBinding

private var TAG = HistoryFragment::class.java.name

class HistoryFragment : BaseFragment() {

    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater)
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