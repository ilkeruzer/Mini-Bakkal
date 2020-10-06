package com.ilkeruzer.minibakkal.ui.fragment.basket

import androidx.lifecycle.LiveData
import com.ilkeruzer.minibakkal.model.Product
import com.ilkeruzer.minibakkal.repository.BasketRepository
import com.ilkeruzer.minibakkal.ui.BaseViewModel

class BasketViewModel(
    private val basketRepository: BasketRepository
) : BaseViewModel() {

    fun getAllBasket(): LiveData<ArrayList<Product?>> {
        return basketRepository.getAllBasketLiveData()
    }
}