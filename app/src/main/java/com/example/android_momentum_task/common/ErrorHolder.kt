package com.example.android_momentum_task.common

sealed class ErrorHolder(override val message:String):Exception(message){
    data class NetworkConnection(override val message: String) : ErrorHolder(message)
    data class BadRequest(override val message: String) : ErrorHolder(message)
    data class UnAuthorized(override val message: String) : ErrorHolder(message)
    data class InternalServerError(override val message: String) :ErrorHolder(message)
    data class ResourceNotFound(override val message: String) : ErrorHolder(message)
    data class UnExpected(override val message: String) : ErrorHolder(message)
    data class Unknown(override val message: String) : ErrorHolder(message)
}
