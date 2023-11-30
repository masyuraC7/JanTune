package com.jantune.heartdisease.ui.view.main.identification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jantune.heartdisease.data.model.IdentificationHistory
import com.jantune.heartdisease.data.repository.JantuneRepository
import com.jantune.heartdisease.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IdentificationViewModel @Inject constructor(
    private val repository: JantuneRepository
) : ViewModel() {
    private val _isActive = MutableLiveData<Boolean>()
    val isActive: LiveData<Boolean> = _isActive

    private val _isUpdateIdentification = MutableLiveData<List<IdentificationHistory>?>()
    val isUpdateIdentification: MutableLiveData<List<IdentificationHistory>?> =
        _isUpdateIdentification

    fun getActiveIdentification() {
        repository.getActiveIdentification().observeForever { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {}

                    is Result.Success -> {
                        _isUpdateIdentification.value = result.data
                    }

                    is Result.Empty -> {}

                    is Result.Error -> {}
                }
            }
        }
    }

    fun updateIdentification(name: String) {
        repository.updateIdentification(name).observeForever {
            _isActive.value = it
        }
    }
}