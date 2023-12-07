package com.jantune.heartdisease.data.remote.response

import com.google.gson.annotations.SerializedName

data class FailResponse(

	@field:SerializedName("data")
	val data: Any? = null,

	@field:SerializedName("message")
	val message: String? = null
)
