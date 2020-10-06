package com.ilkeruzer.minibakkal


interface IBaseListener {

    interface Adapter<T> {
        fun onItemClick(item: T, position: Int)
        fun onLoadMore(itemCount: Int)
    }

    interface AppBarProductListener {
        fun basketClick()
    }

    interface AppBarBasketListener {
        fun deleteClick()
        fun closeClick()
    }

    interface ProductItemListener<T> {
        fun addBasket(item: T, position: Int)
        fun removeBasket(item: T, position: Int)
    }

    interface BasketStatusListener {
        fun confirmClick()
    }

    interface AlertDialogButtonListener {
        fun positiveClick()
        fun negativeClick()
    }

}