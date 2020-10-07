package com.ilkeruzer.minibakkal.data.service

import com.ilkeruzer.minibakkal.data.DataGateway
import com.ilkeruzer.minibakkal.data.model.BasketPost
import com.ilkeruzer.minibakkal.data.model.BasketResponse
import com.ilkeruzer.minibakkal.model.Product


class ApiService(private val service: IApiService) {

    fun getAllProduct(): DataGateway<ArrayList<Product?>> {
        return DataGateway(
            service.getProductList()
        )
    }

    fun postBasket(basketPost: BasketPost): DataGateway<BasketResponse> {
        return DataGateway(
            service.postBasket(basketPost)
        )
    }
}
