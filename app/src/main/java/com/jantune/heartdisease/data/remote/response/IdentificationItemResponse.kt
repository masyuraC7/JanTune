package com.jantune.heartdisease.data.remote.response

import com.google.gson.annotations.SerializedName

data class IdentificationItemResponse(

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("oldPeak")
    val oldPeak: Int? = null,

    @field:SerializedName("restingEcg")
    val restingEcg: String? = null,

    @field:SerializedName("maxHr")
    val maxHr: Int? = null,

    @field:SerializedName("gender")
    val gender: String? = null,

    @field:SerializedName("fastingBs")
    val fastingBs: Int? = null,

    @field:SerializedName("stSlope")
    val stSlope: String? = null,

    @field:SerializedName("exerciseAngina")
    val exerciseAngina: String? = null,

    @field:SerializedName("restingBp")
    val restingBp: Int? = null,

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
    val chestPainType: String? = null
)