package io.github.mohamedisoliman.forillas.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.mohamedisoliman.forillas.domain.RetrieveAllPosts

class HomeViewModelFactory(
    private val usecase: RetrieveAllPosts,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = HomeViewModel(usecase) as T
}