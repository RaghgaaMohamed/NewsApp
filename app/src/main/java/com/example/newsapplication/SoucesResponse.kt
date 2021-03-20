package com.example.newsapplication

import com.google.gson.annotations.SerializedName

data class SoucesResponse(

	@field:SerializedName("sources")
	val sources: List<SourcesItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("message")
    val message : String? = null
)