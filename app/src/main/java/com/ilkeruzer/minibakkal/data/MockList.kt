package com.ilkeruzer.minibakkal.data

import com.ilkeruzer.minibakkal.model.Product

fun getProductMockList(): ArrayList<Product?> {
    val productList = ArrayList<Product?>()
    val product = Product(
        "5f52348e919ff34aed98d349",
        "Elma",
        6.99,
        "₺",
        "https://desolate-shelf-18786.herokuapp.com/images/elma.png",
        5
    )

    productList.add(product)

    productList.add(Product(
        "5f52348e919ff34aed98d349",
        "Elma",
        6.99,
        "₺",
        "https://desolate-shelf-18786.herokuapp.com/images/elma.png",
        5
    ))

    productList.add(Product(
        "5f52348e919ff34aed98d349",
        "Elma",
        6.99,
        "₺",
        "https://desolate-shelf-18786.herokuapp.com/images/elma.png",
        5
    ))


    return productList
}