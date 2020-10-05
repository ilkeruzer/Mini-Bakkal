package com.ilkeruzer.minibakkal.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ilkeruzer.minibakkal.data.DataGateway
import com.ilkeruzer.minibakkal.data.local.IResult
import com.ilkeruzer.minibakkal.data.local.IResultOb
import com.ilkeruzer.minibakkal.data.local.dao.BasketDao
import com.ilkeruzer.minibakkal.data.local.entities.BasketEntity

class BasketRepository(
    private val basketDao: BasketDao
) {

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

}