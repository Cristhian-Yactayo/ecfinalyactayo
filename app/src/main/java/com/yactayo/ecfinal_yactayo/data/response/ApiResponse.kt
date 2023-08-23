package com.yactayo.ecfinal_yactayo.data.response

import java.lang.Exception

sealed class ApiResponse<T>(data: T? = null, exception: Exception? = null){

    data class Ejecucion<T>(val data: T): ApiResponse<T>(data)
    data class Error<T>(val exception: Exception): ApiResponse<T>(null, exception)

}
