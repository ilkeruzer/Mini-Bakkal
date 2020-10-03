package com.ilkeruzer.minibakkal.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilkeruzer.minibakkal.Constant
import com.ilkeruzer.minibakkal.IBaseListener
import com.ilkeruzer.minibakkal.R

/**
 * Created by İlker Üzer on 23.05.2020.
 */
abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder?>{
    protected var mListObjects: ArrayList<T?>? = null
    protected var isMultipleType: Boolean
    private var nullListTitle: String? = null
    private var loadMoreSize = -1

    constructor(
        listObjects: ArrayList<T?>?,
        isMultipleType: Boolean
    ) {

        this.isMultipleType = isMultipleType
        addNullItem(listObjects)
        mListObjects = listObjects ?: ArrayList()
    }

    constructor(
        listObjects: ArrayList<T?>?,
        isMultipleType: Boolean,
        nullListTitle: String?
    ) {
        this.isMultipleType = isMultipleType
        addNullItem(listObjects)
        mListObjects = listObjects ?: ArrayList()
        this.nullListTitle = nullListTitle
    }



    fun getList(): List<T?>? {
        return mListObjects
    }

    protected abstract fun onBindBaseViewHolder(
        holder: RecyclerView.ViewHolder?,
        position: Int
    )

    protected abstract fun onCreateBaseViewHolder(
        v: ViewGroup?,
        viewType: Int
    ): RecyclerView.ViewHolder?

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        when (viewType) {
            Constant.ITEMTYPE.NULLTYPE -> {
                val vie: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_null, parent, false)
                return ItemNullHolder(vie)
            }
        }
        return onCreateBaseViewHolder(parent, viewType)!!
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (getItemViewType(position)) {
            Constant.ITEMTYPE.NULLTYPE -> {
                //    val vH: ItemNullHolder =
                //        holder as ItemNullHolder
                /*         vH.item!!.animation = AnimationUtils.loadAnimation(
                             getContext(),
                             R.anim.rec_list_null_item
                         )
                 */
                if (nullListTitle != null) {
                    //vH.txtNullListTitle!!.text = nullListTitle
                }
            }
            else -> onBindBaseViewHolder(holder, position)
        }
        if (position >= itemCount - 1 && listener != null && loadMoreSize != itemCount - 1) {
            loadMoreSize = itemCount - 1
            listener?.onLoadMore(itemCount)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mListObjects!![position] == null) {
            Constant.ITEMTYPE.NULLTYPE
        } else if (!isMultipleType) {
            Constant.ITEMTYPE.ONETYPE
        } else {
            super.getItemViewType(position)
        }
    }

    override fun getItemCount(): Int {
        if (mListObjects!!.size == 1 && listener != null) {
            //listener.onListEmpty()
        }
        return if (mListObjects == null) {
            0
        } else {
            mListObjects!!.size
        }
    }

    fun isEmptyList(): Boolean {
        return itemCount == 1
    }

    fun removeItem(position: Int) {
        mListObjects!!.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, mListObjects!!.size)
    }

    fun updateItem(position: Int, obj: T) {
        mListObjects!![position] = obj
        notifyDataSetChanged()
    }

    fun notifyDataSet(list: List<T?>?) {
        mListObjects!!.addAll(list!!)
        addNullItem(mListObjects)
        notifyDataSetChanged()
    }

    fun notifyReload(list: ArrayList<T?>?) {
        addNullItem(list)
        mListObjects = list
        notifyDataSetChanged()
    }

    private fun addNullItem(list: MutableList<T?>?) {
        if (list!!.size == 0) {
            list.add(null)
        }
    }

    protected var listener: IBaseListener.Adapter<T>? = null
    fun setOnActionListener(listener: IBaseListener.Adapter<T>?) {
        this.listener = listener
    }

    class ItemNullHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!) {

    }

    companion object {
        private const val TAG = "BaseRecyclerAdapter"
    }
}