package com.jimmyhernandez.yapotest.ui.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.ittalent.testitandroid.ui.common.Data
import com.ittalent.testitandroid.ui.common.DataState
import com.jimmyhernandez.reigntest.ReignApp
import kotlin.properties.Delegates

inline fun <reified T : Activity> Context.intentFor(body: Intent.() -> Unit): Intent =
    Intent(this, T::class.java).apply(body)

inline fun <reified T : Activity> Context.startActivity(body: Intent.() -> Unit) {
    startActivity(intentFor<T>(body))
}


/* Live data */

fun <T> MutableLiveData<Data<T>>.repostValue(exec: T.() -> Unit = { }) = postValue(value?.apply { data?.apply(exec) })

fun <T> MutableLiveData<Data<T>>.postException(throwable: Throwable) {
    postValue(Data(dataState = DataState.ERROR, exception = throwable))
}

fun <T> MutableLiveData<Data<T>>.postSuccess() {
    postValue(Data(dataState = DataState.SUCCESS, data = null))
}

fun <T> MutableLiveData<Data<T>>.postValue(value: T) {
    postValue(Data(dataState = DataState.SUCCESS, data = value))
}

fun <T> MutableLiveData<Data<T>>.postLoading() {
    postValue(Data(dataState = DataState.LOADING))
}

/* Model View View-Model */

@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> FragmentActivity.getViewModel(crossinline factory: () -> T): T {

    val vmFactory = object : ViewModelProvider.Factory {
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }

    return ViewModelProviders.of(this, vmFactory)[T::class.java]
}

/* Utils */

fun <T> T?.with(block: T.() -> Unit) = this?.let(block)

inline fun <T> T?.notNull(exec: (T) -> Unit): T? = this?.also { exec(this) }

fun AppCompatActivity.finishWithResult(resultCode: Int, data: Intent? = null) {
    setResult(resultCode, data)
    finish()
}

inline fun <VH : RecyclerView.ViewHolder, T> RecyclerView.Adapter<VH>.basicDiffUtil(
    initialValue: List<T>,
    crossinline areItemsTheSame: (T, T) -> Boolean = { old, new -> old == new },
    crossinline areContentsTheSame: (T, T) -> Boolean = { old, new -> old == new }
) =
    Delegates.observable(initialValue) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                areItemsTheSame(old[oldItemPosition], new[newItemPosition])

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                areContentsTheSame(old[oldItemPosition], new[newItemPosition])

            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size
        }).dispatchUpdatesTo(this@basicDiffUtil)
    }

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).apply(requestOptions).into(this)
}



val Context.app: ReignApp
    get() = applicationContext as ReignApp