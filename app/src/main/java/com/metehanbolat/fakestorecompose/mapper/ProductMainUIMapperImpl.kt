package com.metehanbolat.fakestorecompose.mapper

import com.metehanbolat.domain.mapper.ProductListMapper
import com.metehanbolat.domain.model.ProductItem
import com.metehanbolat.fakestorecompose.model.ProductUIData
import javax.inject.Inject

class ProductMainUIMapperImpl @Inject constructor() : ProductListMapper<ProductItem, ProductUIData> {
    override fun map(input: List<ProductItem>?): List<ProductUIData> {
        return input?.map {
            ProductUIData(
                name = it.title ?: "",
                imageUrl = it.image ?: ""
            )
        } ?: emptyList()
    }
}