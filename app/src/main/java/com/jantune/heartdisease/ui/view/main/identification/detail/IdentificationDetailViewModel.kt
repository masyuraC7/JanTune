package com.jantune.heartdisease.ui.view.main.identification.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jantune.heartdisease.data.remote.response.IdentificationItemResponse
import com.jantune.heartdisease.data.repository.JantuneRepository
import com.jantune.heartdisease.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IdentificationDetailViewModel @Inject constructor(
    private val repository: JantuneRepository
) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<String?>()
    val isError: LiveData<String?> = _isError

    private val _isFilledIdentification = MutableLiveData<IdentificationItemResponse?>()
    val isFilledIdentification: LiveData<IdentificationItemResponse?> =
        _isFilledIdentification

    fun getIdentificationById(userId: Int, identificationId: Int) {
        repository.getIdentificationById(userId, identificationId).observeForever { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        _isLoading.value = true
                        _isError.value = null
                    }

                    is Result.Success -> {
                        _isLoading.value = false
                        _isFilledIdentification.value = result.data
                        _isError.value = null
                    }

                    is Result.Empty -> {
                        _isLoading.value = false
                        _isError.value = "Data masih kosong."
                    }

                    is Result.Error -> {
                        _isLoading.value = false
                        _isError.value = "Terjadi kesalahan, coba lagi nanti."
                    }
                }
            }
        }
    }
}