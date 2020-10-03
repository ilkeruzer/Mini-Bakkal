package com.ilkeruzer.minibakkal.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilkeruzer.minibakkal.Constant
import com.ilkeruzer.minibakkal.IBaseListener
import com.ilkeruzer.minibakkal.databinding.ItemBasketLayoutBinding
import com.ilkeruzer.minibakkal.databinding.ItemProductLayoutBinding
import com.ilkeruzer.minibakkal.model.Product
import com.ilkeruzer.minibakkal.util.ImageLoader

class BasketAdapter(
    list: ArrayList<Product?>,
    isMultipleType: Boolean
) : BaseRecyclerAdapter<Product>(list, isMultipleType) {

    private lateinit var binding: ItemBasketLayoutBinding
    private var productItemListener: IBaseListener.ProductItemListener<Product>? = null

    override fun onBindBaseViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val ob: Product = mListObjects!![position]!!
        when (getItemViewType(position)) {
            Constant.ITEMTYPE.ONETYPE -> {
                val vH: BasketHolder = holder as BasketHolder
                productItemListener?.let { vH.bind(ob, it) }
            }
        }
    }

    override fun onCreateBaseViewHolder(v: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemBasketLayoutBinding.inflate(LayoutInflater.from(v!!.context), v, false)
        return BasketHolder(binding)
    }

    class BasketHolder(binding: ItemBasketLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        private var imageView = binding.imageView
        private var addBtn = binding.addCardView
        private var removeBtn = binding.removeCardView
        private var countCard = binding.countCardView
        private var basketText = binding.countText
        private var titleText = binding.nameText
        private var priceText = binding.priceText

        @SuppressLint("SetTextI18n")
        fun bind(
            product: Product,
            listener: IBaseListener.ProductItemListener<Product>
        ) {
            titleText.text = product.name
            priceText.text = "${product.price} ${product.currency}"
            ImageLoader.glideImage(imageView, product.imageUrl)
            addBtn.setOnClickListener { listener.addBasket(product, adapterPosition) }
            removeBtn.setOnClickListener { listener.removeBasket(product, adapterPosition) }
            basketText.text = product.basket.toString()

        }

    }

    fun setProductListener(productItemListener: IBaseListener.ProductItemListener<Product>) {
        this.productItemListener = productItemListener
    }

}