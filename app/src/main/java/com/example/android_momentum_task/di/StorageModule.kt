package com.example.android_momentum_task.di

import android.content.Context
import androidx.room.Room
import com.example.android_momentum_task.data.local.ProductDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): ProductDB {
        return Room.databaseBuilder(
            appContext,
            ProductDB::class.java,
            "Product_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideProductDAO(database: ProductDB) = database.getProductDao()
}