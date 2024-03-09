package com.example.android_momentum_task.ui.events

import com.example.android_momentum_task.data.models.ProductItem

sealed class ProductsAction {
    object Loading : ProductsAction()
    data class Success(val products: List<ProductItem>) : ProductsAction()

    data class Failure(val massage: String) : ProductsAction()
}