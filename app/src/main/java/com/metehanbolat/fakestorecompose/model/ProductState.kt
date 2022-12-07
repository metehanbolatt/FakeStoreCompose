package com.metehanbolat.fakestorecompose.model

import com.metehanbolat.domain.model.ProductItem

data class ProductState(
    val isLoading: Boolean = false,
    val products: List<ProductItem> = emptyList(),
    val error: String = ""
)
