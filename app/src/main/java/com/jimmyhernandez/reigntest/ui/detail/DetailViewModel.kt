package com.jimmyhernandez.reigntest.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ittalent.testitandroid.ui.common.Data
import com.jimmyhernandez.domain.Hit
import com.jimmyhernandez.usecases.FindNewsById
import com.jimmyhernandez.yapotest.ui.common.ScopedViewModel
import com.jimmyhernandez.yapotest.ui.common.postException
import com.jimmyhernandez.yapotest.ui.common.postLoading
import com.jimmyhernandez.yapotest.ui.common.postValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val id: Int, private val findNewsById: FindNewsById) : ScopedViewModel() {

    val model = MutableLiveData<Data<Hit>>()

    init {
        initScope()
    }

    fun findNews(){
        launch {
            model.postLoading()

            runCatching {
                withContext(Dispatchers.IO){
                    findNewsById.invoke(id)
                }
            }.onSuccess { response ->
                if (response.storyId  != 0){
                    Log.e("findNews","findNews storyUrl ${response.storyUrl}")
                    Log.e("findNews","findNews url ${response.url}")
                    model.postValue(response)
                } else {
                    model.postException(Exception("${"Error"}: "))
                }

            }.onFailure { throwable ->
                model.postException(throwable)
            }

        }
    }

}