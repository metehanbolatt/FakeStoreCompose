package com.metehanbolat.data.di

import com.metehanbolat.data.api.FakeStoreApi
import com.metehanbolat.data.repository.FakeStoreRepositoryImpl
import com.metehanbolat.domain.common.Constants.BASE_URL
import com.metehanbolat.domain.repository.FakeStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideFakeStoreApi(): FakeStoreApi = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FakeStoreApi::class.java)

    @Provides
    @Singleton
    fun provideFakeStoreRepository(api: FakeStoreApi): FakeStoreRepository =
        FakeStoreRepositoryImpl(api)
}