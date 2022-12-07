package com.metehanbolat.domain.use_case.get_all_products

import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.model.ProductItem
import com.metehanbolat.domain.repository.FakeStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val repository: FakeStoreRepository
) {
    operator fun invoke(): Flow<NetworkResponse<List<ProductItem>>> = flow {
        try {
            emit(NetworkResponse.Loading())
            val products = repository.getAllProducts()
            emit(NetworkResponse.Success(products))
        } catch (e: IOException) {
            emit(NetworkResponse.Error("Couldn't reach server. Check your internet connection!"))
        }
    }
}