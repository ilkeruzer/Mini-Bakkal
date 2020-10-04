package com.ilkeruzer.minibakkal.data.service

import com.ilkeruzer.minibakkal.model.Product
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET


interface IApiService {

    @GET("list")
    fun getProductList(): Observable<Response<ArrayList<Product?>>>


}
