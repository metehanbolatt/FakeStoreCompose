package com.metehanbolat.fakestorecompose.model

import androidx.annotation.StringRes

sealed class ProductState {
    object Loading : ProductState()
    data class Success(val data: List<ProductUIData>) : ProductState()
    data class Error(@StringRes val message: Int) : ProductState()
}