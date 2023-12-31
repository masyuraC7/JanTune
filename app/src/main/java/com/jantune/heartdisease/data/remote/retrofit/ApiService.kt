package com.jantune.heartdisease.data.remote.retrofit

import com.jantune.heartdisease.data.model.UserModel
import com.jantune.heartdisease.data.remote.response.FailResponse
import com.jantune.heartdisease.data.remote.response.IdentificationResponse
import com.jantune.heartdisease.data.remote.response.UserLoginRequest
import com.jantune.heartdisease.data.remote.response.UserLoginResponse
import com.jantune.heartdisease.data.remote.response.UserRegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService{
    @GET("identification/{userId}")
    suspend fun getAllIdentificationByUserId(
        @Path("userId") userId: Int
    ): IdentificationResponse

    @GET("identification/{userId}/{identificationId}")
    suspend fun getIdentificationById(
        @Path("userId") userId: Int,
        @Path("identificationId") identificationId: Int
    ): IdentificationResponse

    @DELETE("identification/{userId}/{identificationId}")
    suspend fun deleteIdentificationById(
        @Path("userId") userId: Int,
        @Path("identificationId") identificationId: Int
    ): IdentificationResponse

    @POST("users")
    suspend fun userRegister(@Body request: UserRegisterRequest): Response<String>

    @POST("users/login")
    suspend fun userLogin(@Body request: UserLoginRequest): UserLoginResponse

    @GET("users/{userId}")
    suspend fun getUser(
        @Path("userId") userId: Int,
        @Header("Authorization") token: String
    ): Response<List<UserModel>>

    @POST("identification")
    suspend fun createNewIdentification(
        @Field("name") name: String,
        @Field("date") date: String? = null,
        @Field("oldPeak") oldPeak: Int? = null,
        @Field("restingEcg") restingEcg: String? = null,
        @Field("maxHr") maxHr: Int? = null,
        @Field("gender") gender: String? = null,
        @Field("fastingBs") fastingBs: Int? = null,
        @Field("stSlope") stSlope: String? = null,
        @Field("exerciseAngina") exerciseAngina: String? = null,
        @Field("restingBp") restingBp: Int? = null,
        @Field("cholesterol") cholesterol: Int? = null,
        @Field("time") time: String? = null,
        @Field("age") age: Int? = null,
        @Field("chestPainType") chestPainType: String? = null
    ): FailResponse
}