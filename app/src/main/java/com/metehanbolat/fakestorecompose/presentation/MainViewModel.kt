package com.metehanbolat.fakestorecompose.presentation

import androidx.lifecycle.ViewModel
import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.use_case.get_all_products.GetAllProductsUseCase
import com.metehanbolat.fakestorecompose.model.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
): ViewModel() {

    private val _state = MutableStateFlow(ProductState())
    val state: StateFlow<ProductState> = _state.asStateFlow()

    fun getAllProducts() {
        getAllProductsUseCase().onEach { result ->
            when(result) {
                is NetworkResponse.Loading -> {
                    _state.value = ProductState(isLoading = true)
                }
                is NetworkResponse.Success -> {
                    _state.value = ProductState(products = result.data ?: emptyList())
                }
                is NetworkResponse.Error -> {
                    _state.value = ProductState(error = result.message ?: "An unexpected error occurred!")
                }
            }
        }
    }
}