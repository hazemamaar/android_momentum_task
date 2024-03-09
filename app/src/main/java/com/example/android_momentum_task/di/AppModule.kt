package com.example.android_momentum_task.di


import android.content.Context
import com.example.android_momentum_task.data.local.ProductDao
import com.example.android_momentum_task.data.remote.IApiService
import com.example.android_momentum_task.data.repository.MainRepositoryImpl
import com.example.android_momentum_task.domain.repository.IMainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplicationContext(
        @ApplicationContext context: Context,
    ) = context

    @Singleton
    @Provides
    fun provideMainRepository(
        iApiService: IApiService,
        productDao: ProductDao
    ): IMainRepository = MainRepositoryImpl(iApiService, productDao)
}