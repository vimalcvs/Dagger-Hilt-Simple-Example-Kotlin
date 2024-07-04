package com.example.hiltapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltapp.model.ModelMeal
import com.example.hiltapp.repository.RepositoryMain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelMain @Inject constructor(private val repository: RepositoryMain) : ViewModel() {

    private val _isEmpty = MutableLiveData<Boolean>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _isNoNetwork = MutableLiveData<Boolean>()

    private val _mealsData = MutableLiveData<List<ModelMeal>>()

    val isEmpty: LiveData<Boolean> = _isEmpty
    val isLoading: LiveData<Boolean> = _isLoading
    val isNoNetwork: LiveData<Boolean> = _isNoNetwork
    val mealsData: LiveData<List<ModelMeal>> = _mealsData

    init {
        fetchMeals()
    }

    fun fetchMeals() {
        _isLoading.value = true
        _isNoNetwork.value = false
        _isEmpty.value = false

        viewModelScope.launch {
            try {
                val response = repository.getMeals()
                if (response.isSuccessful) {
                    if (response.body()?.meals.isNullOrEmpty()) {
                        _isEmpty.postValue(true)
                    } else {
                        _mealsData.postValue(response.body()?.meals)
                    }
                } else {
                    _isNoNetwork.postValue(true)
                }
            } catch (e: Exception) {
                _isNoNetwork.postValue(true)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
