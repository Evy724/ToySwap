package com.revature.project2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {

    private val _isLoading = MutableStateFlow(true) //Splash screen is initially loading
    val isLoading = _isLoading.asStateFlow()    //establishes as read-only

    init {
        viewModelScope.launch {
            delay(3000) //3 second delay
            _isLoading.value = false
        }
    }
}