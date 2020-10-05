package com.ilkeruzer.minibakkal.data.local.dao

import androidx.room.*
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

    @Delete
    fun delete(basketEntity: BasketEntity) : Completable

    @Query("SELECT * FROM basket_table ORDER BY id")
    fun getAllBasket() : Observable<MutableList<BasketEntity>>



}