package com.jimmyhernandez.yapotest.ui.common

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.jimmyhernandez.yapotest.ui.common.Scope

abstract class ScopedViewModel : ViewModel(), Scope by Scope.Impl() {

    init {
        initScope()
    }

    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}