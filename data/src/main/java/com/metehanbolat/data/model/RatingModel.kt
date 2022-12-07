package com.metehanbolat.data.model


import com.google.gson.annotations.SerializedName

data class RatingModel(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("rate")
    val rate: Double?
)