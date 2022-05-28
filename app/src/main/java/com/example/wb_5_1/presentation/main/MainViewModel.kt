package com.example.wb_5_1.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.wb_5_1.utils.Resource
import com.example.wb_5_1.domain.model.DotaHeroModelDomain
import com.example.wb_5_1.domain.usecase.GetDotaHeroesListUseCase
import kotlinx.coroutines.*

class MainViewModel (private val getDotaHeroesListUseCase: GetDotaHeroesListUseCase): ViewModel() {

    private val _dotaHeroesList = MutableLiveData<List<DotaHeroModelDomain>>()
    val dotaHeroesList: LiveData<List<DotaHeroModelDomain>>
        get() = _dotaHeroesList

    private val _loadingPermission = MutableLiveData<Boolean>()
    val loadingPermission: LiveData<Boolean>
        get() = _loadingPermission

    init {
        _loadingPermission.value = true
    }

    fun getDotaHeroes() = liveData(Dispatchers.IO){
        emit(Resource.Loading(data = null))
        try{
            Log.e("Loading", "Trying to load data from vm")
            emit(Resource.Success(data = getDotaHeroesListUseCase.execute()))
            Log.e("Loading", "Data loaded")
        } catch(e: Exception){
            emit(Resource.Error(data = null, message = e.message ?: "Error Occurred!!!"))
        }
    }

    fun setDotaHeroesList(list: List<DotaHeroModelDomain>){
        _dotaHeroesList.value = list
    }

    fun changeToFalseLoadingPermission(){
        _loadingPermission.value = false
    }

}