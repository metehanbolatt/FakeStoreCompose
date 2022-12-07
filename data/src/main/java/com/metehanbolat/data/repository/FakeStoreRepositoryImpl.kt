package com.metehanbolat.data.repository

import com.metehanbolat.data.api.FakeStoreApi
import com.metehanbolat.data.di.IODispatcher
import com.metehanbolat.data.extensions.toProductItem
import com.metehanbolat.domain.model.ProductItem
import com.metehanbolat.domain.repository.FakeStoreRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FakeStoreRepositoryImpl @Inject constructor(
    private val api: FakeStoreApi,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): FakeStoreRepository {

    override suspend fun getAllProducts(): List<ProductItem> {
        return withContext(ioDispatcher) { api.getAllProducts().map { it.toProductItem() } }
    }

}