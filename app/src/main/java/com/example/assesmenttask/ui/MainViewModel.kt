package com.example.assesmenttask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assesmenttask.data.model.UserDetails
import com.example.assesmenttask.data.remote.ApiRepository
import com.example.assesmenttask.handler.CustomResponse
import kotlinx.coroutines.launch

class MainViewModel(
    private val  apiRepository: ApiRepository
) : ViewModel() {

    private val userDetailsLd = MutableLiveData<MutableList<UserDetails?>>()
    private val errorMessageLd = MutableLiveData<String>()

    val error: LiveData<String> = errorMessageLd
    val userDetails: LiveData<MutableList<UserDetails?>> = userDetailsLd

    fun getUserDetails() {
        viewModelScope.launch {
            when (val response = apiRepository.getUserDetails()) {
                is CustomResponse.Success -> userDetailsLd.value = response.data
                is CustomResponse.Failure -> errorMessageLd.value =
                    response.error.message
            }
        }
    }
}