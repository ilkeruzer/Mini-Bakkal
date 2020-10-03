package com.ilkeruzer.minibakkal.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel(), LifecycleObserver {

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unSubscribeViewModel() {
        compositeDisposable.dispose()
    }

}
