package com.ilkeruzer.minibakkal.util

import com.ilkeruzer.minibakkal.data.local.entities.BasketEntity
import com.ilkeruzer.minibakkal.model.Product
import java.math.BigDecimal
import java.math.RoundingMode

object AppUtil {

    fun productToEntity(product: Product) : BasketEntity {
        return BasketEntity(
            product.id,
            product.name,
            product.price,
            product.imageUrl,
            product.stock,
            product.basket
        )
    }

    fun entityToProduct(entity: BasketEntity): Product {
        return Product(
            entity.id,
            entity.name,
            entity.price,
            "₺",
            entity.imageUrl,
            entity.stock,
            entity.basketStock
        )
    }

    fun mergeBasketCount(localeList: ArrayList<Product>, remoteList: ArrayList<Product?>) : ArrayList<Product?> {
        if (localeList.size > 0) {
            for (product in remoteList) {
                for (fProduct in localeList) {
                    if (fProduct.id == product!!.id) product.basket = fProduct.basket
                }
            }
        }
        return remoteList
    }

    fun doubleToDecimal(value: Double): BigDecimal? {
        return BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN)
    }
}