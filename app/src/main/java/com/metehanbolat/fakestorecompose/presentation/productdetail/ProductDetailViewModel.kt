package com.metehanbolat.fakestorecompose.presentation.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.model.ProductItem
import com.metehanbolat.domain.usecase.getproductfromid.GetProductFromIdUseCase
import com.metehanbolat.fakestorecompose.R
import com.metehanbolat.fakestorecompose.model.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductFromIdUseCase: GetProductFromIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ProductState<ProductItem>>(ProductState.Loading)
    val state: StateFlow<ProductState<ProductItem>> = _state.asStateFlow()

    fun getProductFromId(id: String) {
        viewModelScope.launch {
            getProductFromIdUseCase(id = id)
                .onStart { println("getProductFromId: onStart") }
                .onCompletion { println("getProductFromId: onCompletion") }
                .collect { response ->
                    when (response) {
                        NetworkResponse.Loading -> _state.value = ProductState.Loading
                        is NetworkResponse.Error -> _state.value = ProductState.Error(R.string.error)
                        is NetworkResponse.Success -> _state.value = ProductState.Success(data = response.result)
                    }
                }
        }
    }

}