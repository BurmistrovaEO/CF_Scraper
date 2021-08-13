package com.example.codeforces_parsed.database

import androidx.lifecycle.*
import kotlinx.coroutines.launch

/**
 * Define a model class to use as the data source
 */

class ContestViewModel(private val repository: ContestListRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allContests : LiveData<List<ContestList>> = liveData { repository.allContests }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(contestList: ContestList) = viewModelScope.launch {
        repository.insert(contestList)
    }
}

class ContestViewModelFactory(private val repository: ContestListRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContestViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContestViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}