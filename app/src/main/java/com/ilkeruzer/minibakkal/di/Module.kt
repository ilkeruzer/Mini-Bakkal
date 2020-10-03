package com.ilkeruzer.minibakkal.di

import com.ilkeruzer.minibakkal.data.service.ApiService
import com.ilkeruzer.minibakkal.data.service.ApiServiceChief
import com.ilkeruzer.minibakkal.data.service.IApiService
import org.koin.dsl.module

val appModule = module {

}

val networkModule = module {
    single { ApiService(ApiServiceChief.getRetrofit()!!.create(IApiService::class.java)) }
}

val viewModelModule = module {

}