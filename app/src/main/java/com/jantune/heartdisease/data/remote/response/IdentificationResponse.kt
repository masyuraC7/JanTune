package com.jantune.heartdisease.data.remote.response

import com.google.gson.annotations.SerializedName

data class IdentificationResponse(

	@field:SerializedName("data")
	val data: List<IdentificationItemResponse>? = null,

	@field:SerializedName("message")
	val message: String? = null
)
