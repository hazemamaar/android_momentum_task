package com.example.android_momentum_task.data.local

import androidx.room.TypeConverter
import com.example.android_momentum_task.data.models.Rating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converts {
    private val gson = Gson()

    @TypeConverter
    fun ratingToString(rating: Rating): String {
        return gson.toJson(rating)
    }

    @TypeConverter
    fun stringToRating(rating: String): Rating {
        val listType = object : TypeToken<Rating>() {
        }.type
        return gson.fromJson(rating, listType)
    }
}