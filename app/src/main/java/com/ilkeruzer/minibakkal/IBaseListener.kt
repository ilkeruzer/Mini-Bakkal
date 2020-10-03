package com.ilkeruzer.minibakkal


interface IBaseListener {

    interface Adapter<T> {

        fun onItemClick(item: T, position: Int)
        fun onLoadMore(itemCount: Int)

    }

}