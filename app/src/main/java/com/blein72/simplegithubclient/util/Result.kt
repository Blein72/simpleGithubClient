package com.blein72.simplegithubclient.util

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
}

val NoBodyException = Exception("No response body")