package com.jantune.heartdisease.data.repository

import androidx.lifecycle.LiveData
import com.jantune.heartdisease.data.model.IdentificationHistory
import com.jantune.heartdisease.utils.Result

interface JantuneRepository{
    fun getActiveIdentification(): LiveData<Result<List<IdentificationHistory>>>
    fun updateIdentification(name: String): LiveData<Boolean>
}