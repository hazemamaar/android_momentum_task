package com.example.android_momentum_task.common

import com.google.gson.annotations.SerializedName

data class HttpErrorEntity(
    @SerializedName("message") val errorMessage: String,
    @SerializedName("status") val errorCode: Int
)
