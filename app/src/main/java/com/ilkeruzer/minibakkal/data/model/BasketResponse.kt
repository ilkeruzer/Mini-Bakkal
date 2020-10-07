package com.ilkeruzer.minibakkal.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class BasketResponse(
    @SerializedName("orderID") val orderID: String?,
    @SerializedName("message") val message: String
)