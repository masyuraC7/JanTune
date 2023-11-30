package com.jantune.heartdisease.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.jantune.heartdisease.data.model.IdentificationHistory
import com.jantune.heartdisease.data.model.dummyIdentificationHistory
import com.jantune.heartdisease.utils.Result

class JantuneRepositoryImpl : JantuneRepository {
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
}