package com.ilkeruzer.minibakkal.ui.fragment.basket

import androidx.lifecycle.LiveData
import com.ilkeruzer.minibakkal.model.Product
import com.ilkeruzer.minibakkal.repository.BasketRepository
import com.ilkeruzer.minibakkal.ui.BaseViewModel
import com.ilkeruzer.minibakkal.util.AppUtil

class BasketViewModel(
    private val basketRepository: BasketRepository
) : BaseViewModel() {

    fun getAllBasket(): LiveData<ArrayList<Product?>> {
        return basketRepository.getAllBasketLiveData()
    }

    fun saveBasket(product: Product): LiveData<Boolean> {
        return basketRepository.insertBasket(AppUtil.productToEntity(product))
    }

    fun updateBasketItem(product: Product): LiveData<Boolean> {
        return basketRepository.updateBasket(AppUtil.productToEntity(product))
    }

    fun deleteBasketItem(product: Product): LiveData<Boolean> {
        return basketRepository.deleteBasketItem(AppUtil.productToEntity(product))
    }
}