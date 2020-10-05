package com.ilkeruzer.minibakkal.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ilkeruzer.minibakkal.data.local.entities.BasketEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface BasketDao {

    @Insert
    fun insert(basketEntity: BasketEntity) : Completable

    @Query("SELECT SUM(basketStock) FROM basket_table ORDER BY id")
    fun basketCount() : Observable<Int>

    @Update
    fun update(basketEntity: BasketEntity) : Completable

}