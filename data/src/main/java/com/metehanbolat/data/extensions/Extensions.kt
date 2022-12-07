package com.metehanbolat.data.extensions

import com.metehanbolat.data.model.ProductsItemModel
import com.metehanbolat.domain.model.ProductItem
import com.metehanbolat.domain.model.Rating

fun ProductsItemModel.toProductItem(): ProductItem {
    val rating = rating?.let { Rating(it.count, it.rate) }
    return ProductItem(
        category = category,
        description = description,
        id = id,
        image = image,
        price = price,
        rating = rating,
        title = title
    )
}