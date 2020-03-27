package com.jimmyhernandez.reigntest.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ittalent.testitandroid.ui.common.Data
import com.jimmyhernandez.domain.HackerNewsResponse
import com.jimmyhernandez.usecases.GetListNewsUseCase
import com.jimmyhernandez.yapotest.ui.common.ScopedViewModel
import com.jimmyhernandez.yapotest.ui.common.postException
import com.jimmyhernandez.yapotest.ui.common.postLoading
import com.jimmyhernandez.yapotest.ui.common.postValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val getListNewsUseCase: GetListNewsUseCase) : ScopedViewModel() {

    val model = MutableLiveData<Data<HackerNewsResponse>>()

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
                if (response.hits!!.isNotEmpty()){
                    Log.e("gitListNewsResponse", "gitListNewsResponse $response")
                    model.postValue(response)
                } else {
                    Log.e("gitListNewsResponse", "gitListNewsResponse $response")
                    model.postException(Exception("${"Error"}: ${response.hits!!.isEmpty().toString()}"))
                }

            }.onFailure { throwable ->
                model.postException(throwable)
                Log.e("gitListNewsResponse", "throwable $throwable")
            }

        }
    }

}