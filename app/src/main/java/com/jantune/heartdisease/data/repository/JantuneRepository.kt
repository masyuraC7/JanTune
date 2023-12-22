package com.jantune.heartdisease.data.repository

import androidx.lifecycle.LiveData
import com.jantune.heartdisease.data.model.IdentificationHistory
import com.jantune.heartdisease.data.remote.response.IdentificationItemResponse
import com.jantune.heartdisease.data.remote.response.UserLoginResponse
import com.jantune.heartdisease.utils.Result

interface JantuneRepository {
    fun getAllIdentificationById(userId: Int): LiveData<Result<List<IdentificationItemResponse>>>
    fun getIdentificationById(userId: Int, identificationId: Int): LiveData<Result<IdentificationItemResponse>>
    fun deleteIdentificationById(userId: Int, identificationId: Int): LiveData<String>
    fun getActiveIdentification(): LiveData<Result<List<IdentificationHistory>>>
    fun updateIdentification(name: String): LiveData<Boolean>
    suspend fun userRegister(name: String, email: String, password: String): LiveData<String>
    suspend fun userLogin(email: String, password: String): LiveData<Result<UserLoginResponse>>
}