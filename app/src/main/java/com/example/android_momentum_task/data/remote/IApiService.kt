package com.example.android_momentum_task.data.remote

import com.example.android_momentum_task.data.models.ProductItem
import retrofit2.http.GET

interface IApiService {
    @GET("products")
    suspend fun getProducts(): List<ProductItem>
}