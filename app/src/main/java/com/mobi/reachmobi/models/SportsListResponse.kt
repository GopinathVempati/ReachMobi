package com.mobi.reachmobi.models

import com.google.gson.annotations.SerializedName

data class SportsListResponse(

	@field:SerializedName("sports")
	val sports: List<SportsItem>? = null
)

data class SportsItem(

	@field:SerializedName("idSport")
	val idSport: String? = null,

	@field:SerializedName("strFormat")
	val strFormat: String? = null,

	@field:SerializedName("strSport")
	val strSport: String? = null,

	@field:SerializedName("strSportIconGreen")
	val strSportIconGreen: String? = null,

	@field:SerializedName("strSportThumb")
	val strSportThumb: String? = null,

	@field:SerializedName("strSportDescription")
	val strSportDescription: String? = null
)
