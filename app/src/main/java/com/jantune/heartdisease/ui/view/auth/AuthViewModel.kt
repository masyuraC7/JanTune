package com.jantune.heartdisease.ui.view.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jantune.heartdisease.data.repository.JantuneRepository
import kotlinx.coroutines.launch
import com.jantune.heartdisease.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: JantuneRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg

    init {
        _isLoading.value = false
    }
    // Fungsi Registrasi
    fun userRegister(name: String, email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = userRepository.userRegister(name, email, password)
                when (result) {
                    is Result.Success -> {
                        _errorMsg.value = "Success Register"
                    }
                    is Result.Error -> _errorMsg.value = "Terjadi kesalahan, coba lagi nanti."
                    else -> {}
                }
            } catch (e: Exception) {
                _errorMsg.value = "Terjadi kesalahan, coba lagi nanti."
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Fungsi Login
    fun userLogin(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = userRepository.userLogin(email, password)
                when (result) {
                    is Result.Success -> _errorMsg.value = "Success login"
                    is Result.Error -> _errorMsg.value =  "Terjadi kesalahan, coba lagi nanti."
                    else -> {}
                }
            } catch (e: Exception) {
                _errorMsg.value = "Terjadi kesalahan, coba lagi nanti."
            } finally {
                _isLoading.value = false
            }
        }
    }


}

