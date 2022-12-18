package com.abdelrahman.rafaat.movies.base

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.abdelrahman.rafaat.movies.utils.ConnectionLiveData

private var TAG = BaseFragment::class.java.name

open class BaseFragment : Fragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
        checkConnection()
    }

    private fun checkConnection() {
        ConnectionLiveData.getInstance(requireContext()).observe(requireActivity()) {
            if (it) {
                Log.d(TAG, "checkConnection: Base")
                connected()
            } else {
                Log.d(TAG, "checkConnection: Base")
                disconnected()
            }
        }
    }

    protected open fun connected() {}

    protected open fun disconnected() {}

    protected fun downloadImage(imageLink: String, movieName: String) {
        val manager: DownloadManager =
            requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(imageLink)
        val request = DownloadManager.Request(uri)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, movieName)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        manager.enqueue(request)
    }

}