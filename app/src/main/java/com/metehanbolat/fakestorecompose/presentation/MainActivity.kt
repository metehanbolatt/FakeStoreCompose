package com.metehanbolat.fakestorecompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.metehanbolat.fakestorecompose.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.metehanbolat.fakestorecompose.model.ProductState
import com.metehanbolat.fakestorecompose.presentation.allproduct.ProductCard
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
                            ProductCard(product = it)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun WaitingScreen(resId: Int) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resId = resId))
    Box(
        modifier = Modifier.size(200.dp)
    ) {
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
    }
}