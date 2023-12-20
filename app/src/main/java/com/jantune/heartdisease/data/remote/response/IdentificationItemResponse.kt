package com.jantune.heartdisease.data.remote.response

import com.google.gson.annotations.SerializedName

data class IdentificationItemResponse(

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("restingECG")
    val restingECG: Int? = null,

    @field:SerializedName("maxHR")
    val maxHR: Int? = null,

    @field:SerializedName("sex")
    val sex: Int? = null,

    @field:SerializedName("fastingBS")
    val fastingBS: Int? = null,

    @field:SerializedName("stSlope")
    val stSlope: Int? = null,

    @field:SerializedName("userId")
    val userId: Int? = null,

    @field:SerializedName("exerciseAngina")
    val exerciseAngina: Int? = null,

    @field:SerializedName("result")
    val result: String? = null,

    @field:SerializedName("oldpeak")
    val oldpeak: Float? = null,

    @field:SerializedName("restingBP")
    val restingBP: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("cholesterol")
    val cholesterol: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("time")
    val time: String? = null,

    @field:SerializedName("age")
    val age: Int? = null,

    @field:SerializedName("chestPainType")
    val chestPainType: Int? = null
)