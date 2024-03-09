package com.example.android_momentum_task.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.android_momentum_task.data.local.Converts
import kotlinx.parcelize.Parcelize

@Parcelize
@TypeConverters(Converts::class)
@Entity(tableName = "product_table")
data class ProductItem(
    val category: String,
    val description: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating? = Rating(20, 2.6),
    val title: String
) : Parcelable