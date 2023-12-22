package com.jantune.heartdisease.ui.view.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jantune.heartdisease.data.preferences.UserPreferences
import com.jantune.heartdisease.data.repository.JantuneRepository
import com.jantune.heartdisease.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: JantuneRepository,
    private val preferences: UserPreferences
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg

    private val _userToken = MutableLiveData<String>()
    val userToken: LiveData<String> = _userToken

    init {
        _isLoading.value = false
    }

    // Fungsi Registrasi
    fun userRegister(name: String, email: String, password: String) {
        viewModelScope.launch {
            userRepository.userRegister(name, email, password).observeForever { result ->
                _errorMsg.value = result
            }
        }
    }

    // Fungsi Login
    fun userLogin(email: String, password: String) {
        viewModelScope.launch {
            userRepository.userLogin(email, password).observeForever { result ->
                if (result != null) {
                    when (result) {
                        is Result.Loading -> {
                            _isLoading.value = true
                        }

                        is Result.Success -> {
                            _isLoading.value = false
                            _userToken.value = result.data.token
                            _errorMsg.value = "Login Berhasil"
                        }

                        is Result.Error -> {
                            _isLoading.value = false
                            _errorMsg.value = result.error
                        }

                        else -> {}
                    }
                }
            }
        }
    }

//    fun getUserToken(): LiveData<String> {
//        return preferences.getUserToken().asLiveData()
//    }

    fun saveUserSession(token: String, id: Int) {
        viewModelScope.launch {
            preferences.saveUserSession(token, id)
        }
    }

    fun deleteUserSession() {
        viewModelScope.launch {
            preferences.deleteUserSession()
        }
    }
}
