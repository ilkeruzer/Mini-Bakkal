package com.ilkeruzer.minibakkal.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ilkeruzer.minibakkal.data.local.dao.BasketDao
import com.ilkeruzer.minibakkal.data.local.entities.BasketEntity

@Database(entities = [BasketEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun basketDao(): BasketDao
}