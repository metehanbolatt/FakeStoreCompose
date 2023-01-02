package com.metehanbolat.fakestorecompose.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.metehanbolat.fakestorecompose.presentation.allproduct.MainScreen
import com.metehanbolat.fakestorecompose.presentation.productdetail.ProductDetailScreen

@ExperimentalMaterialApi
@ExperimentalGlideComposeApi
@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.AllProduct.route
    ) {
        composable(route = Screen.AllProduct.route) {
            MainScreen(navController)
        }
        composable(route = Screen.ProductDetail.route) {
            ProductDetailScreen()
        }
    }
}