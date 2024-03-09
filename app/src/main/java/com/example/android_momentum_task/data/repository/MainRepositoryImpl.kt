package com.example.android_momentum_task.data.repository

import com.example.android_momentum_task.data.local.ProductDao
import com.example.android_momentum_task.data.models.ProductItem
import com.example.android_momentum_task.data.remote.IApiService
import com.example.android_momentum_task.domain.repository.IMainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val iApiService: IApiService,
    private val productDao: ProductDao
) : IMainRepository {
    override suspend fun getProductsFromRemote(): List<ProductItem> =
        iApiService.getProducts()


    override suspend fun getProductsFromLocal(): Flow<List<ProductItem>> {
        return productDao.getProducts()
    }

    override suspend fun insertProductsToLocal(products: List<ProductItem>): List<Long> {
        return productDao.insertProducts(products)
    }

}