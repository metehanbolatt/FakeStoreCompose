package com.metehanbolat.fakestorecompose.model

import androidx.annotation.StringRes

sealed class ProductState<out T: Any?> {
    object Loading : ProductState<Nothing>()
    data class Success<out T: Any>(val data: T) : ProductState<T>()
    data class Error(@StringRes val message: Int) : ProductState<Nothing>()
}