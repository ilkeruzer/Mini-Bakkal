package com.ilkeruzer.minibakkal.ui.custom

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
* Created by İlker Üzer on 22.03.2020.
*/
class RecList : RecyclerView {

    constructor(context: Context) : super(context){
        init(context,null)
    }

    constructor(context: Context, attrs: AttributeSet):    super(context, attrs){
        init(context,attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context,attrs)
    }



    private fun init(context: Context, attrs: AttributeSet?) {

    }


    fun setRecyclerView(isVertical: Boolean) {
        layoutManager = if (isVertical) {
            LinearLayoutManager(
                context
            )
        } else {
            LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        }
        hasFixedSize()
    }


    fun setGridColumn(gridSize: Int) {
        GridLayoutManager(
            context, gridSize,
            VERTICAL, false
        ).apply {
            layoutManager = this
        }
        hasFixedSize()
    }


}