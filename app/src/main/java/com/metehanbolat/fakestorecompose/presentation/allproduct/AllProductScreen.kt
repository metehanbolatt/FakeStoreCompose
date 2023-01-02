package com.metehanbolat.fakestorecompose.presentation.allproduct

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.metehanbolat.fakestorecompose.R
import com.metehanbolat.fakestorecompose.model.ProductState
import com.metehanbolat.fakestorecompose.navigation.Screen
import com.metehanbolat.fakestorecompose.presentation.MainViewModel
import com.metehanbolat.fakestorecompose.presentation.WaitingScreen

@ExperimentalMaterialApi
@ExperimentalGlideComposeApi
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsState()
    var searchText by remember { mutableStateOf("") }
    var isTextFieldEnabled by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = searchText) {
        if (searchText.isBlank()) {
            viewModel.getAllProducts()
        } else {
            viewModel.getLimitedProducts(searchText)
        }
    }

    ProductListScreen(
        searchText = searchText,
        onValueChange = { searchText = it },
        isTextFieldEnabled = isTextFieldEnabled,
        productListScreen = {
            when (val response = uiState) {
                ProductState.Loading -> {
                    WaitingScreen(R.raw.loading)
                    isTextFieldEnabled = false
                }
                is ProductState.Error -> {
                    WaitingScreen(R.raw.network_error)
                }
                is ProductState.Success -> {
                    isTextFieldEnabled = true
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(response.data) {
                            ProductCard(product = it) {
                                navController.navigate(Screen.ProductDetail.route)
                            }
                        }
                    }
                }
            }
        }
    )
}