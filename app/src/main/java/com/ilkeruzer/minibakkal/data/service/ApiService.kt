package com.ilkeruzer.minibakkal.data.service

import com.ilkeruzer.minibakkal.data.DataGateway
import com.ilkeruzer.minibakkal.model.Product


class ApiService(private val service: IApiService) {

    fun getAllProduct(): DataGateway<ArrayList<Product?>> {
        return DataGateway(
            service.getProductList()
        )
    }
}
