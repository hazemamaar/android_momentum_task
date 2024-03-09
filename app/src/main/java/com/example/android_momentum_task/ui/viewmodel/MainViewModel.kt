package com.example.android_momentum_task.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_momentum_task.common.NetworkConnection
import com.example.android_momentum_task.common.Resource
import com.example.android_momentum_task.domain.usecase.GetProductsFromLocalUseCase
import com.example.android_momentum_task.domain.usecase.GetProductsFromRemoteUseCase
import com.example.android_momentum_task.ui.events.ProductsAction
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val getProductsFromLocalUseCase: GetProductsFromLocalUseCase,
    private val getProductsFromRemoteUseCase: GetProductsFromRemoteUseCase,
) : ViewModel() {
    private val _productsUIState =
        MutableStateFlow<ProductsAction>(ProductsAction.Success(emptyList()))
    val productsUIState: StateFlow<ProductsAction> get() = _productsUIState
    private val networkConnection = NetworkConnection(context)
    fun getProducts(viewLifeCycle: LifecycleOwner) {

        networkConnection.observe(viewLifeCycle) { isConnected ->
            if (isConnected) {
                getProductsFromRemoteUseCase().onEach {
                    when (it) {
                        is Resource.Error -> {
                            _productsUIState.emit(ProductsAction.Failure(it.message!!))
                        }

                        is Resource.Loading -> {
                            _productsUIState.emit(ProductsAction.Loading)
                        }

                        is Resource.Success -> {
                            _productsUIState.emit(ProductsAction.Success(it.data!!))
                        }
                    }
                }.launchIn(viewModelScope)
            } else {
                getProductsFromLocalUseCase.invoke().onEach {
                    when (it) {
                        is Resource.Error -> {
                            _productsUIState.emit(ProductsAction.Failure(it.message!!))
                        }

                        is Resource.Loading -> {
                            _productsUIState.emit(ProductsAction.Loading)
                        }

                        is Resource.Success -> {
                            _productsUIState.emit(ProductsAction.Success(it.data!!))
                        }
                    }
                }.launchIn(viewModelScope)
            }


        }
    }

}