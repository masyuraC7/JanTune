package com.jantune.heartdisease.ui.view.main.identification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jantune.heartdisease.data.model.IdentificationHistory
import com.jantune.heartdisease.data.remote.response.IdentificationItemResponse
import com.jantune.heartdisease.data.repository.JantuneRepository
import com.jantune.heartdisease.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class IdentificationViewModel @Inject constructor(
    private val repository: JantuneRepository
) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<String?>()
    val isError: LiveData<String?> = _isError

    private val _isSuccessMessage = MutableLiveData<String?>()
    val isSuccessMessage: LiveData<String?> = _isSuccessMessage

    private val _isFilledIdentification = MutableLiveData<List<IdentificationItemResponse>?>()
    val isFilledIdentification: LiveData<List<IdentificationItemResponse>?> =
        _isFilledIdentification

    fun getAllIdentificationByUserId(userId: Int) {
        repository.getAllIdentificationById(userId).observeForever { result ->
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

    fun deleteIdentificationByID(userId: Int, identificationId: Int){
        repository.deleteIdentificationById(userId, identificationId).observeForever {
            _isSuccessMessage.value = it
        }
    }
}