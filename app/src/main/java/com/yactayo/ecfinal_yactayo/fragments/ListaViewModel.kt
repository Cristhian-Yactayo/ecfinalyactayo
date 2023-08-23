package com.yactayo.ecfinal_yactayo.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yactayo.ecfinal_yactayo.data.repository.ListaRepository
import com.yactayo.ecfinal_yactayo.data.response.ApiResponse
import com.yactayo.ecfinal_yactayo.model.ListaModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListaViewModel: ViewModel() {

    val repository = ListaRepository()
    val listaList: MutableLiveData<List<ListaModel>> = MutableLiveData<List<ListaModel>>()

    fun getListarFromService(){

        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getListar()
            when(response){
                is ApiResponse. Error ->{
                    //Error a colocar
                }
                is ApiResponse.Ejecucion ->{
                    listaList.postValue(response.data.data)
                }
            }
        }

    }


}