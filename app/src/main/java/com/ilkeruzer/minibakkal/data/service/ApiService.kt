package com.ilkeruzer.minibakkal.data.service

import com.ilkeruzer.minibakkal.model.Product


class ApiService(private val service: IApiService) {

    fun getAllProduct(): ApiServiceGateway<ArrayList<Product?>> {
        return ApiServiceGateway(
            service.getProductList()
        )
    }
}
