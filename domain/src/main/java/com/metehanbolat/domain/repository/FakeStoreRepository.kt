package com.metehanbolat.domain.repository

import com.metehanbolat.domain.model.ProductItem

interface FakeStoreRepository {

    suspend fun getAllProducts(): List<ProductItem>
}