package com.metehanbolat.data.di.usecase

import com.metehanbolat.domain.usecase.get_all_products.GetAllProductsUseCase
import com.metehanbolat.domain.usecase.get_all_products.GetAllProductsUseCaseImpl
import com.metehanbolat.domain.usecase.getlimitedproductsusecase.GetLimitedProductsUseCase
import com.metehanbolat.domain.usecase.getlimitedproductsusecase.GetLimitedProductsUseCaseImpl
import com.metehanbolat.domain.usecase.getproductfromid.GetProductFromIdUseCase
import com.metehanbolat.domain.usecase.getproductfromid.GetProductFromIdUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindGetAllProductsUseCase(getAllProductsUseCaseImpl: GetAllProductsUseCaseImpl): GetAllProductsUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetLimitedProductsUseCase(getLimitedProductsUseCaseImpl: GetLimitedProductsUseCaseImpl): GetLimitedProductsUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetProductFromIdUseCase(getProductFromIdUseCaseImpl: GetProductFromIdUseCaseImpl): GetProductFromIdUseCase
}