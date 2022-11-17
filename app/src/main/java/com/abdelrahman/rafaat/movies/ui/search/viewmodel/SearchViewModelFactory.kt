package com.abdelrahman.rafaat.movies.ui.search.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abdelrahman.rafaat.movies.model.RepositoryInterface
import java.lang.IllegalArgumentException

class SearchViewModelFactory(private val _irepo: RepositoryInterface) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            SearchViewModel(_irepo) as T
        } else {
            throw IllegalArgumentException("View Model Class Not Found")
        }
    }
}

