package com.ilkeruzer.minibakkal.ui.fragment.products

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ilkeruzer.minibakkal.IBaseListener.AppBarProductListener
import com.ilkeruzer.minibakkal.IBaseListener.ProductItemListener
import com.ilkeruzer.minibakkal.R
import com.ilkeruzer.minibakkal.data.getProductMockList
import com.ilkeruzer.minibakkal.databinding.ProductsFragmentBinding
import com.ilkeruzer.minibakkal.model.Product
import com.ilkeruzer.minibakkal.ui.adapter.ProductAdapter
import com.ilkeruzer.minibakkal.ui.fragment.BaseFragment
import org.koin.android.ext.android.inject

class ProductsFragment : BaseFragment<ProductsViewModel>(), AppBarProductListener,
    ProductItemListener<Product> {

    private val vM by inject<ProductsViewModel>()
    private lateinit var binding: ProductsFragmentBinding
    private val productAdapter by inject<ProductAdapter>()

    override fun equalsViewModel(savedInstanceState: Bundle?) {
        viewModel = vM
    }

    override fun getViewBindingRoot(inflater: LayoutInflater, container: ViewGroup?): View? {
        binding = ProductsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getViewModel(): Class<ProductsViewModel> {
        return ProductsViewModel::class.java
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        binding.appTabBar.setProductListener(this)
        initRecycler()
        observeProduct()
    }

    private fun observeProduct() {
        showLoading()
        viewModel.getProductLiveData().observe(viewLifecycleOwner, {
            binding.recList.visibility = View.VISIBLE
            stopLoading()
            productAdapter.notifyReload(it)

        })
    }

    private fun initRecycler() {
        binding.recList.apply {
            visibility = View.GONE
            setGridColumn(3)
            adapter = productAdapter
        }
        productAdapter.setProductListener(this)
    }

    override fun basketClick() {
        findNavController().navigate(R.id.productToBasket)
    }

    override fun addBasket(item: Product, position: Int) {
        Log.d("ProductsFragment", "addBasket: ")
        item.basket += 1
        productAdapter.updateItem(position, item)
    }

    override fun removeBasket(item: Product, position: Int) {
        Log.d("ProductsFragment", "removeBasket: ")
        item.basket -= 1
        productAdapter.updateItem(position, item)
    }


}