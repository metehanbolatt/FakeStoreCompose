package com.metehanbolat.data.model


import com.google.gson.annotations.SerializedName

data class ProductsItemModel(
    @SerializedName("category")
    val category: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("rating")
    val rating: RatingModel?,
    @SerializedName("title")
    val title: String?
)