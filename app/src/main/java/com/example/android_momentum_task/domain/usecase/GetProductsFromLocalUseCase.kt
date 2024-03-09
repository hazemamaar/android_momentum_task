package com.example.android_momentum_task.domain.usecase

import com.example.android_momentum_task.common.HandleErrorHelper
import com.example.android_momentum_task.common.Resource
import com.example.android_momentum_task.data.models.ProductItem
import com.example.android_momentum_task.domain.repository.IMainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductsFromLocalUseCase @Inject constructor(
    private val repository: IMainRepository,
) {
    operator fun invoke(): Flow<Resource<List<ProductItem>, String>> = flow {
        emit(Resource.Loading())
        try {
            repository.getProductsFromLocal().collect {
                emit(Resource.Success(it))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message, null, HandleErrorHelper.asNetworkException(e)))
        }
    }
}