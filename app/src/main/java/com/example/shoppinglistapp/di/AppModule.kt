package com.example.shoppinglistapp.di

import com.androiddevs.shoppinglisttestingyt.data.local.ShoppingDao
import com.example.shoppinglistapp.data.remote.PixabayAPI
import com.example.shoppinglistapp.repositories.DefaultShoppingRepository
import com.example.shoppinglistapp.repositories.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        dao: ShoppingDao,
        api: PixabayAPI
    ) = DefaultShoppingRepository(dao, api) as ShoppingRepository
}