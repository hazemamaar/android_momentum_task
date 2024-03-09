package com.example.android_momentum_task.domain.repository

import com.example.android_momentum_task.data.models.ProductItem
import kotlinx.coroutines.flow.Flow

interface IMainRepository {
    suspend fun getProductsFromRemote(): List<ProductItem>
    suspend fun getProductsFromLocal(): Flow<List<ProductItem>>
    suspend fun insertProductsToLocal(products: List<ProductItem>): List<Long>
}