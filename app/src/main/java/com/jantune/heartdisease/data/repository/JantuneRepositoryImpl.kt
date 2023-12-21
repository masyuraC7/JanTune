package com.jantune.heartdisease.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.jantune.heartdisease.data.model.IdentificationHistory
import com.jantune.heartdisease.data.model.UserModel
import com.jantune.heartdisease.data.model.dummyIdentificationHistory
import com.jantune.heartdisease.data.remote.response.ApiResponse
import com.jantune.heartdisease.data.remote.response.IdentificationItemResponse
import com.jantune.heartdisease.data.remote.response.UserLoginRequest
import com.jantune.heartdisease.data.remote.response.UserLoginResponse
import com.jantune.heartdisease.data.remote.response.UserRegisterRequest
import com.jantune.heartdisease.data.remote.retrofit.ApiService
import com.jantune.heartdisease.utils.Result
import javax.inject.Inject

class JantuneRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : JantuneRepository {
    override fun getAllIdentificationById(userId: Int):
            LiveData<Result<List<IdentificationItemResponse>>> =
        liveData {
            emit(Result.Loading)

            val getUserId = 1 //perlu ambil dari preferences login user

            try {
                val response = apiService.getAllIdentificationByUserId(getUserId)
                val items = response.data

                if (items != null)
                    if (items.isNotEmpty())
                        emit(Result.Success(items))
                    else
                        emit(Result.Empty)
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    override fun getIdentificationById(
        userId: Int,
        identificationId: Int
    ): LiveData<Result<IdentificationItemResponse>> = liveData {
        emit(Result.Loading)

        try {
            val response = apiService.getAllIdentificationByUserId(userId)
            val items = response.data?.first()

            if (items != null)
                emit(Result.Success(items))
            else
                emit(Result.Empty)
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun deleteIdentificationById(
        userId: Int,
        identificationId: Int
    ): LiveData<String> = liveData {
        val getUserId = 1

        try {
            val response = apiService.deleteIdentificationById(getUserId, identificationId)
            val message = response.message.toString()

            emit(message)
        } catch (e: Exception) {
            emit(e.message.toString())
        }
    }

    override fun getActiveIdentification(): LiveData<Result<List<IdentificationHistory>>> =
        liveData {
            emit(Result.Loading)

            val identification = dummyIdentificationHistory.filter {
                it.isActive.and(true)
            }

            if (identification.isNotEmpty()) {
                emit(Result.Success(identification))
            } else {
                emit(Result.Empty)
            }
        }

    override fun updateIdentification(name: String): LiveData<Boolean> =
        liveData {
            val index = dummyIdentificationHistory.indexOfFirst {
                it.name == name
            }

            val itemIdent = dummyIdentificationHistory[index]
            dummyIdentificationHistory[index] =
                itemIdent.copy(isActive = false)

            emit(true)
        }

    override suspend fun userRegister(name: String, email: String, password: String): Result<UserModel> {
        return try {
            val response = apiService.userRegister(UserRegisterRequest(name, email, password))

            if (response.data != null) {
                Result.Success(response.data)
            } else {
                Result.Error("Response data is null")
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }
    override suspend fun userLogin(email: String, password: String): Result<UserLoginResponse> {
        return try {
            val response = apiService.userLogin(UserLoginRequest(email, password))

            if (response.data != null) {
                Result.Success(response.data)
            } else {
                Result.Error("Response data is null")
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

}