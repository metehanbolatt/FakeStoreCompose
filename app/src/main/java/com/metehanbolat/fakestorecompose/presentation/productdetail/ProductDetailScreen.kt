package com.metehanbolat.fakestorecompose.presentation.productdetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.metehanbolat.fakestorecompose.R
import com.metehanbolat.fakestorecompose.model.ProductState
import com.metehanbolat.fakestorecompose.presentation.WaitingScreen

@ExperimentalGlideComposeApi
@Composable
fun ProductDetailScreen(
    id: String,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getProductFromId(id = id)
    }

    val uiState by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val response = uiState) {
            ProductState.Loading -> {
                WaitingScreen(R.raw.loading)
            }
            is ProductState.Error -> {
                WaitingScreen(R.raw.network_error)
            }
            is ProductState.Success -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(50.dp))
                    Card(
                        modifier = Modifier
                            .size(200.dp)
                            .padding(5.dp),
                        elevation = 10.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            GlideImage(
                                model = response.data.image,
                                contentDescription = stringResource(id = R.string.limited_product),
                                modifier = Modifier.size(140.dp)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = response.data.title.toString(),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = response.data.description.toString()
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = response.data.price.toString() + " ₺",
                        textAlign = TextAlign.End
                    )
                }

            }
        }
    }
}

@ExperimentalGlideComposeApi
@Preview(showBackground = true)
@Composable
fun ProductDetailScreenPreview() {
    ProductDetailScreen("1")
}