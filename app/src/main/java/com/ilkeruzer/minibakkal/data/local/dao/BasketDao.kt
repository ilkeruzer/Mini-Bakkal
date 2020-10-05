package com.ilkeruzer.minibakkal.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.ilkeruzer.minibakkal.data.local.entities.BasketEntity
import io.reactivex.Completable

@Dao
interface BasketDao {

    @Insert
    fun insert(basketEntity: BasketEntity) : Completable

}