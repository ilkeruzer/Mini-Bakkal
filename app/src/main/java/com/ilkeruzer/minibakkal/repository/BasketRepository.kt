package com.ilkeruzer.minibakkal.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ilkeruzer.minibakkal.data.DataGateway
import com.ilkeruzer.minibakkal.data.local.IResult
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
                Log.d("BasketRepository", "onError: ")
                liveData.postValue(false)
            }

        })

        return liveData
    }
}