package com.yactayo.ecfinal_yactayo.data.retrofit

import com.yactayo.ecfinal_yactayo.data.response.ListaListReponse
import retrofit2.http.GET

interface ListaService {

    @GET("br")
    suspend fun getListar(): ListaListReponse

}