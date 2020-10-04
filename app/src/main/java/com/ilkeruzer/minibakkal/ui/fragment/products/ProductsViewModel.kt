package com.ilkeruzer.minibakkal.ui.fragment.products

import androidx.lifecycle.LiveData
import com.ilkeruzer.minibakkal.model.Product
import com.ilkeruzer.minibakkal.repository.ProductRepository
import com.ilkeruzer.minibakkal.ui.BaseViewModel

class ProductsViewModel(
    private val productRepository: ProductRepository
) : BaseViewModel() {

    fun getProductLiveData(): LiveData<ArrayList<Product?>> {
        return productRepository.getProductList()
    }
}