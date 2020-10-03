package com.ilkeruzer.minibakkal.ui.fragment.products

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ilkeruzer.minibakkal.IBaseListener
import com.ilkeruzer.minibakkal.data.getProductMockList
import com.ilkeruzer.minibakkal.databinding.ProductsFragmentBinding
import com.ilkeruzer.minibakkal.model.Product
import com.ilkeruzer.minibakkal.ui.adapter.ProductAdapter
import com.ilkeruzer.minibakkal.ui.fragment.BaseFragment
import org.koin.android.ext.android.inject

class ProductsFragment : BaseFragment<ProductsViewModel>(),IBaseListener.AppBarProductListener,
    IBaseListener.Adapter<Product> {

    private val vM by inject<ProductsViewModel>()
    private lateinit var binding: ProductsFragmentBinding
    private val productAdapter by inject<ProductAdapter>()

    override fun equalsViewModel(savedInstanceState: Bundle?) {
        viewModel = vM
    }

    override fun getViewBindingRoot(inflater: LayoutInflater, container: ViewGroup?): View? {
        binding = ProductsFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun getViewModel(): Class<ProductsViewModel> {
        return ProductsViewModel::class.java
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        binding.appTabBar.setProductListener(this)
        initRecycler()
    }

    private fun initRecycler() {
        binding.recList.apply {
            setGridColumn(3)
            adapter = productAdapter
        }
        productAdapter.setOnActionListener(this)

        productAdapter.notifyReload(getProductMockList())
    }

    override fun basketClick() {
        Log.d("ProductsFragment", "basketClick: ")
    }

    override fun onItemClick(item: Product, position: Int) {

    }

    override fun onLoadMore(itemCount: Int) {

    }


}