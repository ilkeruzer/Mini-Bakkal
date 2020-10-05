package com.ilkeruzer.minibakkal.data.local

interface IResultOb<T> {
    fun onSuccess(t: T)

    fun onError()
}