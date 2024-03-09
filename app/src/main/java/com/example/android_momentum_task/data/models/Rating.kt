package com.example.android_momentum_task.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating(
    val count: Int ?= null,
    val rate: Double ?= null
):Parcelable