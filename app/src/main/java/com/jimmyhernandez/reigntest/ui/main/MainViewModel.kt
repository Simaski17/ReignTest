package com.jimmyhernandez.reigntest.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ittalent.testitandroid.ui.common.Data
import com.jimmyhernandez.domain.Hit
import com.jimmyhernandez.usecases.GetCountNewsUseCase
import com.jimmyhernandez.usecases.GetListNewsUseCase
import com.jimmyhernandez.usecases.GetNewsWithoutDeleteUseCase
import com.jimmyhernandez.usecases.UpdateNewsUseCase
import com.jimmyhernandez.yapotest.ui.common.ScopedViewModel
import com.jimmyhernandez.yapotest.ui.common.postException
import com.jimmyhernandez.yapotest.ui.common.postLoading
import com.jimmyhernandez.yapotest.ui.common.postValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val getListNewsUseCase: GetListNewsUseCase, private val updateNewsUseCase: UpdateNewsUseCase,
                    private val getCountNewsUseCase: GetCountNewsUseCase, private val getNewsWithoutDeleteUseCase: GetNewsWithoutDeleteUseCase) : ScopedViewModel() {

    val model = MutableLiveData<Data<List<Hit>>>()
    val modelDelete = MutableLiveData<Data<Hit>>()
    val modelCount = MutableLiveData<Data<Boolean>>()

    init {
        initScope()
    }

    fun gitListNewsResponse(){
        launch {
            model.postLoading()

            runCatching {
                withContext(Dispatchers.IO){
                    getListNewsUseCase.invoke()
                }
            }.onSuccess { response ->
                if (response.isNotEmpty()){
                    model.postValue(response)
                } else {
                    model.postException(Exception("${"Error"}: ${response.isEmpty().toString()}"))
                }

            }.onFailure { throwable ->
                model.postException(throwable)
            }

        }
    }

    fun onDeleteClicked(hit: Hit){
        launch {
            modelDelete.postLoading()

            runCatching {
                withContext(Dispatchers.IO){
                    updateNewsUseCase.invoke(hit)
                }
            }.onSuccess { response ->
                if (response.createdAtI != 0){
                    modelDelete.postValue(response)
                } else {
                    modelDelete.postException(Exception("${"No hay Noticias con ese id en la base de datos"}: "))
                }

            }.onFailure { throwable ->
                modelDelete.postException(throwable)
            }

        }
    }

    fun getCount() {
        launch {
            modelCount.postLoading()

            runCatching {
                withContext(Dispatchers.IO) {
                    getCountNewsUseCase.invoke()
                }
            }.onSuccess { response ->
                modelCount.postValue(response)
            }.onFailure {
            }
        }
    }

    fun getNewsWithoutDelete(){
        launch {
            model.postLoading()

            runCatching {
                withContext(Dispatchers.IO) {
                    getNewsWithoutDeleteUseCase.invoke()
                }
            }.onSuccess { response ->
                if (response.isNotEmpty()) {
                    model.postValue(response)
                } else {
                    model.postException(Exception("${"Error"}: ${response.isEmpty().toString()}"))
                }
            }.onFailure {

            }
        }
    }

}