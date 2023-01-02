package com.metehanbolat.fakestorecompose.navigation

sealed class Screen(val route: String) {
    object AllProduct: Screen(route = "all_product_screen")
    object ProductDetail: Screen(route = "product_detail_screen")
}
