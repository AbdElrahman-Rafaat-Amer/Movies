package com.abdelrahman.rafaat.movies.ui.home.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abdelrahman.rafaat.movies.model.RepositoryInterface
import java.lang.IllegalArgumentException

class HomeViewModelFactory(private val _irepo: RepositoryInterface) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(_irepo) as T
        } else {
            throw IllegalArgumentException("View Model Class Not Found")
        }
    }
}

