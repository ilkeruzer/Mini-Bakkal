package com.ilkeruzer.minibakkal.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class BasketPost(
    @SerializedName("products") var productList: List<ProductPost?>
)

data class ProductPost(
    var id : String,
    var amount: Int
)