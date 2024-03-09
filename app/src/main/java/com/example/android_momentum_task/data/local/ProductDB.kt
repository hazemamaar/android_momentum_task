package com.example.android_momentum_task.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android_momentum_task.data.models.ProductItem


@Database(entities = [ProductItem::class], version = 2, exportSchema = false)
@TypeConverters(Converts::class)
abstract class ProductDB : RoomDatabase() {
    abstract fun getProductDao(): ProductDao

}