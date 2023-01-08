package com.metehanbolat.fakestorecompose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.mapper.ProductListMapper
import com.metehanbolat.domain.model.ProductItem
import com.metehanbolat.domain.usecase.get_all_products.GetAllProductsUseCase
import com.metehanbolat.domain.usecase.getlimitedproductsusecase.GetLimitedProductsUseCase
import com.metehanbolat.fakestorecompose.R
import com.metehanbolat.fakestorecompose.model.ProductState
import com.metehanbolat.fakestorecompose.model.ProductUIData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getLimitedProductsUseCase: GetLimitedProductsUseCase,
    private val productsMapper: ProductListMapper<ProductItem, ProductUIData>
) : ViewModel() {

    private val _state = MutableStateFlow<ProductState<List<ProductUIData>>>(ProductState.Loading)
    val state: StateFlow<ProductState<List<ProductUIData>>> = _state.asStateFlow()

    fun getAllProducts() {
        viewModelScope.launch {
            getAllProductsUseCase()
                .onStart { println("getAllProducts: onStart") }
                .onCompletion { println("getAllProducts: onCompletion") }
                .collect { result ->
                    when (result) {
                        is NetworkResponse.Loading -> {
                            _state.value = ProductState.Loading
                        }
                        is NetworkResponse.Success -> {
                            _state.value =
                                ProductState.Success(data = productsMapper.map(result.result))
                        }
                        is NetworkResponse.Error -> {
                            _state.value = ProductState.Error(message = R.string.error)
                        }
                    }
                }
        }
    }

    fun getLimitedProducts(limit: String) {
        viewModelScope.launch {
            getLimitedProductsUseCase(limit = limit)
                .onStart { println("getLimitedProducts: onStart") }
                .onCompletion { println("getLimitedProducts: onCompletion") }
                .collect { result ->
                    when (result) {
                        is NetworkResponse.Loading -> {
                            _state.value = ProductState.Loading
                        }
                        is NetworkResponse.Success -> {
                            _state.value =
                                ProductState.Success(data = productsMapper.map(result.result))
                        }
                        is NetworkResponse.Error -> {
                            _state.value = ProductState.Error(message = R.string.error)
                        }
                    }
                }
        }
    }
}