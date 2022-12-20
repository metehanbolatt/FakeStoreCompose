package com.metehanbolat.fakestorecompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.metehanbolat.fakestorecompose.model.ProductState
import com.metehanbolat.fakestorecompose.presentation.allproduct.ProductListScreen
import com.metehanbolat.fakestorecompose.ui.theme.FakeStoreComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalGlideComposeApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeStoreComposeTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@ExperimentalGlideComposeApi
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsState()
    var searchText by remember { mutableStateOf("") }

    when (val response = uiState) {
        ProductState.Loading -> {
            LoadingScreen()
        }
        is ProductState.Success -> {
            ProductListScreen(
                productList = response.data,
                searchText = searchText,
                onValueChange = { searchText = it }
            )
        }
        is ProductState.Error -> {
            ErrorScreen()
        }
    }

    LaunchedEffect(key1 = searchText) {
        if (searchText.isBlank()) {
            viewModel.getAllProducts()
        } else {
            viewModel.getLimitedProducts(searchText)
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Loading")
    }
}

@Composable
fun ErrorScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Something went wrong!")
    }
}