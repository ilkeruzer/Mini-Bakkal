package com.ilkeruzer.minibakkal.data.service

interface IResource<T> {
    fun onSuccess(t: T)
    //fun onUnauthorized()
    //fun onError()
    fun onFailed()
}