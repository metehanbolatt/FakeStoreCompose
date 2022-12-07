package com.metehanbolat.data.repository

import com.metehanbolat.data.api.FakeStoreApi
import com.metehanbolat.data.extensions.toProductItem
import com.metehanbolat.domain.model.ProductItem
import com.metehanbolat.domain.repository.FakeStoreRepository
import javax.inject.Inject

class FakeStoreRepositoryImpl @Inject constructor(
    private val api: FakeStoreApi
): FakeStoreRepository {

    override suspend fun getAllProducts(): List<ProductItem> {
        return api.getAllProducts().map { it.toProductItem() }
    }

}