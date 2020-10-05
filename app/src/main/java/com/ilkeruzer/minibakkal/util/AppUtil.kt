package com.ilkeruzer.minibakkal.util

import com.ilkeruzer.minibakkal.data.local.entities.BasketEntity
import com.ilkeruzer.minibakkal.model.Product

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
            " TL",
            entity.imageUrl,
            entity.stock,
            entity.basketStock
        )
    }
}