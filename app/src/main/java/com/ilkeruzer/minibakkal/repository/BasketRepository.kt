package com.ilkeruzer.minibakkal.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ilkeruzer.minibakkal.data.DataGateway
import com.ilkeruzer.minibakkal.data.local.IResult
import com.ilkeruzer.minibakkal.data.local.IResultOb
import com.ilkeruzer.minibakkal.data.local.dao.BasketDao
import com.ilkeruzer.minibakkal.data.local.entities.BasketEntity
import com.ilkeruzer.minibakkal.data.model.BasketPost
import com.ilkeruzer.minibakkal.data.model.BasketResponse
import com.ilkeruzer.minibakkal.data.service.ApiService
import com.ilkeruzer.minibakkal.data.service.IResource
import com.ilkeruzer.minibakkal.model.Product
import com.ilkeruzer.minibakkal.util.AppUtil

class BasketRepository(
    private val basketDao: BasketDao,
    private val service: ApiService
) {
    private val TAG = "BasketRepository"

    fun insertBasket(basketEntity: BasketEntity): LiveData<Boolean> {
        val liveData = MutableLiveData<Boolean>()
        DataGateway<Boolean>(
            basketDao.insert(basketEntity)
        ).localeResponse(object : IResult {
            override fun onSuccess() {
                Log.d("BasketRepository", "onSuccess: ")
                liveData.postValue(true)
            }

            override fun onError() {
                Log.e("BasketRepository", "onError: ")
                liveData.postValue(false)
            }

        })

        return liveData
    }

    fun getBasketCount(): LiveData<Int> {
        val liveData = MutableLiveData<Int>()
        DataGateway(
            basketDao.basketCount(), "ROOM"
        ).localeResponse(object : IResultOb<Int> {
            override fun onSuccess(t: Int) {
                liveData.postValue(t)
            }

            override fun onError() {
                Log.e("BasketRepository", "onError: ")
            }

        })
        return liveData
    }

    fun updateBasket(basketEntity: BasketEntity): LiveData<Boolean> {
        val liveData = MutableLiveData<Boolean>()
        DataGateway<Boolean>(
            basketDao.update(basketEntity)
        ).localeResponse(object : IResult {
            override fun onSuccess() {
                Log.d("BasketRepository", "updateBasket onSuccess: ")
                liveData.postValue(true)
            }

            override fun onError() {
                Log.d("BasketRepository", "updateBasket onError: ")
                liveData.postValue(false)
            }

        })

        return liveData
    }

    fun deleteBasketItem(basketEntity: BasketEntity): LiveData<Boolean> {
        val liveData = MutableLiveData<Boolean>()
        DataGateway<Boolean>(
            basketDao.delete(basketEntity)
        ).localeResponse(object : IResult {
            override fun onSuccess() {
                Log.d("BasketRepository", "deleteBasketItem onSuccess: ")
                liveData.postValue(true)
            }

            override fun onError() {
                Log.d("TAG", "deleteBasketItem onError: ")
                liveData.postValue(false)
            }

        })

        return liveData
    }

    fun getAllBasket(): ArrayList<Product> {
        val list = ArrayList<Product>()
        DataGateway(
            basketDao.getAllBasket(), "ROOM"
        ).localeResponse(object : IResultOb<MutableList<BasketEntity>> {
            override fun onSuccess(t: MutableList<BasketEntity>) {
                Log.d("BasketRepository", "getAllBasket onSuccess: ")
                for (entity in t) {
                    list.add(AppUtil.entityToProduct(entity))
                }

            }

            override fun onError() {
                Log.d("BasketRepository", "getAllBasket onError: ")
            }

        })
        return list
    }

    fun getAllBasketLiveData(): LiveData<ArrayList<Product?>> {
        val liveData = MutableLiveData<ArrayList<Product?>>()
        DataGateway(
            basketDao.getAllBasket(), "ROOM"
        ).localeResponse(object : IResultOb<MutableList<BasketEntity>> {
            override fun onSuccess(t: MutableList<BasketEntity>) {
                Log.d("BasketRepository", "getAllBasket onSuccess: ")
                val list = ArrayList<Product?>()
                for (entity in t) {
                    list.add(AppUtil.entityToProduct(entity))
                }
                liveData.postValue(list)
            }

            override fun onError() {
                Log.d("BasketRepository", "getAllBasket onError: ")
            }

        })
        return liveData
    }

    fun dropBasket(): LiveData<Boolean> {
        val liveData = MutableLiveData<Boolean>()
        DataGateway<Boolean>(
            basketDao.dropTable()
        ).localeResponse(object : IResult {
            override fun onSuccess() {
                Log.d(TAG, "dropBasket onSuccess: ")
                liveData.postValue(true)
            }

            override fun onError() {
                Log.e(TAG, "dropBasket onError: ")
                liveData.postValue(false)
            }

        })
        return liveData
    }

    fun getBasketSumPrice(): LiveData<Double> {
        val liveData = MutableLiveData<Double>()
        DataGateway(
            basketDao.basketSumPrice(), "ROOM"
        ).localeResponse(object : IResultOb<Double> {
            override fun onSuccess(t: Double) {
                liveData.postValue(t)
            }

            override fun onError() {
                Log.e("BasketRepository", "getBasketSumPrice onError: ")
            }

        })
        return liveData
    }

    fun postBasket(basketPost: BasketPost): LiveData<BasketResponse> {
        val liveData = MutableLiveData<BasketResponse>()
        service.postBasket(basketPost)
            .apiResponse(object : IResource<BasketResponse> {
                override fun onSuccess(t: BasketResponse) {
                    liveData.postValue(t)
                }

                override fun onFailed() {
                    Log.e(TAG, "onFailed: ")
                }

            })
        return liveData
    }

}