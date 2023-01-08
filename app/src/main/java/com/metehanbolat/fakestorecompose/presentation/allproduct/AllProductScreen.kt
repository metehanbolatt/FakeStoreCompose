package com.metehanbolat.fakestorecompose.presentation.allproduct

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
                    isTextFieldEnabled = true
                }
                is ProductState.Success -> {
                    isTextFieldEnabled = true
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize(),
                        columns = GridCells.Adaptive(150.dp)
                    ) {
                        val data = response.data
                        items(data.size) { index ->
                            ProductCard(product = data[index]) {
                                navController.navigate("${Screen.ProductDetail.route}/${data[index].id}")
                            }
                        }
                    }
                }
            }
        }
    )
}