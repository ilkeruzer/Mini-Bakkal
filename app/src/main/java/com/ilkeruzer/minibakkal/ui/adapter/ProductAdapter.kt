package com.ilkeruzer.minibakkal.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilkeruzer.minibakkal.Constant
import com.ilkeruzer.minibakkal.IBaseListener
import com.ilkeruzer.minibakkal.databinding.ItemProductLayoutBinding
import com.ilkeruzer.minibakkal.model.Product
import com.ilkeruzer.minibakkal.util.ImageLoader

class ProductAdapter(
    list: ArrayList<Product?>,
    isMultipleType: Boolean
) : BaseRecyclerAdapter<Product>(list, isMultipleType) {

    private lateinit var binding: ItemProductLayoutBinding
    private var productItemListener: IBaseListener.ProductItemListener<Product>? = null

    override fun onBindBaseViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val ob: Product = mListObjects!![position]!!
        when (getItemViewType(position)) {
            Constant.ITEMTYPE.ONETYPE -> {
                val vH: ProductHolder = holder as ProductHolder
                productItemListener?.let { vH.bind(ob, it) }
            }
        }
    }

    override fun onCreateBaseViewHolder(v: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemProductLayoutBinding.inflate(LayoutInflater.from(v!!.context), v, false)
        return ProductHolder(binding)
    }

    class ProductHolder(binding: ItemProductLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        private var imageView = binding.imageView
        private var addBtn = binding.addCardView
        private var removeBtn = binding.removeCardView
        private var countCard = binding.countCardView
        private var basketText = binding.countText

        fun bind(
            product: Product,
            listener: IBaseListener.ProductItemListener<Product>
        ) {
            ImageLoader.glideImage(imageView, product.imageUrl)
            addBtn.setOnClickListener { listener.addBasket(product, adapterPosition) }
            removeBtn.setOnClickListener { listener.removeBasket(product, adapterPosition) }

            if (product.basket <= 0) {
                removeBtn.visibility = View.GONE
                countCard.visibility = View.GONE
            } else {
                removeBtn.visibility = View.VISIBLE
                countCard.visibility = View.VISIBLE
                basketText.text = product.basket.toString()
            }
        }

    }

    fun setProductListener(productItemListener: IBaseListener.ProductItemListener<Product>) {
        this.productItemListener = productItemListener
    }

}