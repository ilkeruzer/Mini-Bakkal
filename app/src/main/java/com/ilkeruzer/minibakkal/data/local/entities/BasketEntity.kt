package com.ilkeruzer.minibakkal.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket_table")
data class BasketEntity(
    @PrimaryKey(autoGenerate = false) var id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "price") var price : Double,
    @ColumnInfo(name = "imageUrl") var imageUrl: String,
    @ColumnInfo(name = "stock") var stock: Int,
    @ColumnInfo(name = "basketStock") var basketStock: Int,
    @ColumnInfo(name = "sumPrice") val sumPrice: Double = price * basketStock
)