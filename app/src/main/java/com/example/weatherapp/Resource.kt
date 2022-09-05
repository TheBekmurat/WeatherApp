package com.example.weatherapp

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String,data : T? = null) : Resource<T>(message = message)
}

//sealed class Resourc<T>(
//    val data: T? = null,
//    val error: Throwable? = null
//){
//    class Success<T>(data: T): Resourc<T>(data)
//    class Loading<T>(data: T? = null): Resourc<T>(data)
//    class Error<T>(throwable: Throwable,data: T? = null): Resourc<T>(data,throwable)
//}