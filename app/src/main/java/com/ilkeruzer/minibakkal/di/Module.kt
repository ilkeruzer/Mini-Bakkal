package com.ilkeruzer.minibakkal.di

import androidx.room.Room
import com.ilkeruzer.minibakkal.data.local.AppDatabase
import com.ilkeruzer.minibakkal.data.service.ApiService
import com.ilkeruzer.minibakkal.data.service.ApiServiceChief
import com.ilkeruzer.minibakkal.data.service.IApiService
import com.ilkeruzer.minibakkal.repository.ProductRepository
import com.ilkeruzer.minibakkal.ui.adapter.BasketAdapter
import com.ilkeruzer.minibakkal.ui.adapter.ProductAdapter
import com.ilkeruzer.minibakkal.ui.fragment.basket.BasketViewModel
import com.ilkeruzer.minibakkal.ui.fragment.products.ProductsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ProductAdapter(ArrayList(), false) }
    single { BasketAdapter(ArrayList(), false) }

    single { ProductRepository(get()) }
}

val networkModule = module {
    single { ApiService(ApiServiceChief.getRetrofit()!!.create(IApiService::class.java)) }
}

val localeModule = module {

    single { Room.databaseBuilder(get(), AppDatabase::class.java, "basket_db").build() }

    single { get<AppDatabase>().basketDao() }
}


val viewModelModule = module {
    viewModel { ProductsViewModel(get()) }
    viewModel { BasketViewModel() }
}