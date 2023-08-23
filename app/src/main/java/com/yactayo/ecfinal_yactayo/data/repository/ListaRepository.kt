package com.yactayo.ecfinal_yactayo.data.repository

import com.yactayo.ecfinal_yactayo.data.response.ApiResponse
import com.yactayo.ecfinal_yactayo.data.response.ListaListReponse
import com.yactayo.ecfinal_yactayo.data.retrofit.ServiceInstance

class ListaRepository {

    suspend fun getListar(): ApiResponse<ListaListReponse>{

        return try {
            val response = ServiceInstance.getListaService().getListar()
            ApiResponse.Ejecucion(response)
        }catch (e: Exception){
            ApiResponse.Error(e)
        }

    }

}