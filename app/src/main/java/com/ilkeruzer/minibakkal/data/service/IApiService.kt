package com.ilkeruzer.minibakkal.data.service

import com.ilkeruzer.minibakkal.data.model.BasketPost
import com.ilkeruzer.minibakkal.data.model.BasketResponse
import com.ilkeruzer.minibakkal.model.Product
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface IApiService {

    @GET("list")
    fun getProductList(): Observable<Response<ArrayList<Product?>>>

    @POST("checkout")
    fun postBasket(@Body basketPost: BasketPost) : Observable<Response<BasketResponse>>
}
