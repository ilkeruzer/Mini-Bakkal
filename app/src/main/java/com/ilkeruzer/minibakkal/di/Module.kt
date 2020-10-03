package com.ilkeruzer.minibakkal.di

import com.ilkeruzer.minibakkal.data.service.ApiService
import com.ilkeruzer.minibakkal.data.service.ApiServiceChief
import com.ilkeruzer.minibakkal.data.service.IApiService
import com.ilkeruzer.minibakkal.ui.adapter.ProductAdapter
import com.ilkeruzer.minibakkal.ui.fragment.basket.BasketViewModel
import com.ilkeruzer.minibakkal.ui.fragment.products.ProductsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ProductAdapter(ArrayList(),false) }
}

val networkModule = module {
    single { ApiService(ApiServiceChief.getRetrofit()!!.create(IApiService::class.java)) }
}

val viewModelModule = module {
    viewModel { ProductsViewModel() }
    viewModel { BasketViewModel() }
}