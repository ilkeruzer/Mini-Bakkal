package com.ilkeruzer.minibakkal.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket_table")
data class BasketEntity(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "product_id") var productId: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "price") var price : Double,
    @ColumnInfo(name = "imageUrl") var imageUrl: String,
    @ColumnInfo(name = "basketStock") var basketStock: Int
)