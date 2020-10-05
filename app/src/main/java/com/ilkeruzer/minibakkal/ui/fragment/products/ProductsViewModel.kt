package com.ilkeruzer.minibakkal.ui.fragment.products

import androidx.lifecycle.LiveData
import com.ilkeruzer.minibakkal.model.Product
import com.ilkeruzer.minibakkal.repository.BasketRepository
import com.ilkeruzer.minibakkal.repository.ProductRepository
import com.ilkeruzer.minibakkal.ui.BaseViewModel
import com.ilkeruzer.minibakkal.util.AppUtil

class ProductsViewModel(
    private val productRepository: ProductRepository,
    private val basketRepository: BasketRepository
) : BaseViewModel() {

    fun getProductLiveData(): LiveData<ArrayList<Product?>> {
        return productRepository.getProductList()
    }

    fun saveBasket(product: Product): LiveData<Boolean> {
        return basketRepository.insertBasket(AppUtil.productToEntity(product))
    }

    fun getBasketCount(): LiveData<Int> {
        return basketRepository.getBasketCount()
    }

    fun updateBasketItem(product: Product): LiveData<Boolean> {
        return basketRepository.updateBasket(AppUtil.productToEntity(product))
    }
}