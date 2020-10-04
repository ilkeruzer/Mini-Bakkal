package com.ilkeruzer.minibakkal.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ilkeruzer.minibakkal.data.service.ApiService
import com.ilkeruzer.minibakkal.data.service.IResource
import com.ilkeruzer.minibakkal.model.Product

class ProductRepository(
    private val service: ApiService
) {
    fun getProductList(): LiveData<ArrayList<Product?>> {
        val liveData = MutableLiveData<ArrayList<Product?>>()
        service.getAllProduct()
            .apiResponse(object : IResource<ArrayList<Product?>> {
                override fun onSuccess(t: ArrayList<Product?>) {
                    liveData.postValue(t)
                }

                override fun onFailed() {
                    Log.e("ProductRepository", "onFailed: ")
                }

            })
        return liveData
    }
}