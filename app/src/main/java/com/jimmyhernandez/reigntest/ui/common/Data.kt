package com.ittalent.testitandroid.ui.common

data class Data<out T>(val dataState: DataState,
                       val data: T? = null,
                       val exception: Throwable? = null)