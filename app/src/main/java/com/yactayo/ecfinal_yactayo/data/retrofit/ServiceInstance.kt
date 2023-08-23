package com.yactayo.ecfinal_yactayo.data.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://fortnite-api.com/v2/cosmetics/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getListaService(): ListaService = retrofit.create(ListaService::class.java)

}