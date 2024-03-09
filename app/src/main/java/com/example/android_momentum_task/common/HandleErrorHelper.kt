package com.example.android_momentum_task.common


import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.HttpException
import java.io.IOException

object HandleErrorHelper {
     fun asNetworkException(ex: Throwable): ErrorHolder {
        return when (ex) {
            is IOException -> {
                ErrorHolder.NetworkConnection(
                    "No Internet Connection"
                )
            }

            is HttpException -> extractHttpExceptions(ex)
            else -> ErrorHolder.UnExpected("Something went wrong...")
        }
    }

     fun extractHttpExceptions(ex: HttpException): ErrorHolder {
        val body = ex.response()?.errorBody()
        val gson = GsonBuilder().create()
        val responseBody = gson.fromJson(body.toString(), JsonObject::class.java)
        val errorEntity = gson.fromJson(responseBody, HttpErrorEntity::class.java)
        return when (errorEntity.errorCode) {
            400 ->
                ErrorHolder.BadRequest(errorEntity.errorMessage)

            500 ->
                ErrorHolder.InternalServerError(errorEntity.errorMessage)

            401 ->
                ErrorHolder.UnAuthorized(errorEntity.errorMessage)

            404 ->
                ErrorHolder.ResourceNotFound(errorEntity.errorMessage)

            else ->
                ErrorHolder.Unknown(errorEntity.errorMessage)

        }
    }
}