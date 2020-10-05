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
        observeBasketCount()
    }

    private fun observeBasketCount() {
        viewModel.getBasketCount().observe(viewLifecycleOwner, {
            if (it == null || it == 0) {
                binding.appTabBar.setBadgeCount(0)
            } else {
                binding.appTabBar.setBadgeCount(it)
            }
            Log.d("ProductsFragment", "observeBasket: $it")

        })
    }

    private fun observeProduct() {
        showLoading()
        viewModel.getProductLiveData().observe(viewLifecycleOwner, {
            binding.recList.visibility = View.VISIBLE
            stopLoading()
            productAdapter.notifyReload(it)

        })

        viewModel.getAllProduct().observe(viewLifecycleOwner, {
            Log.d("ProductsFragment", "observeProduct: $it")
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

        if (item.basket != item.stock) {
            if (item.basket == 0) {
                item.basket += 1
                saveBasketObserve(item)
            }
            else {
                item.basket += 1
                updateBasketObserve(item)
            }

            productAdapter.updateItem(position, item)
        }

    }

    private fun updateBasketObserve(item: Product) {
        Log.d("ProductsFragment", "updateBasketObserve: ")
        viewModel.updateBasketItem(item).observe(viewLifecycleOwner, Observer {
            if (it) Log.d("ProductsFragment", "updateBasketObserve: ")
            else Log.e("ProductsFragment", "updateBasketObserve: ")
        })
    }

    private fun saveBasketObserve(item: Product) {
        viewModel.saveBasket(item).observe(viewLifecycleOwner, {
            if (it) Log.d("ProductsFragment", "saveBasketObserve: ")
            else Log.e("ProductsFragment", "saveBasketObserve: ")
        })
    }

    override fun removeBasket(item: Product, position: Int) {
        Log.d("ProductsFragment", "removeBasket: ")
        item.basket -= 1
        if (item.basket == 0) deleteBasketObserve(item)
        else updateBasketObserve(item)
        productAdapter.updateItem(position, item)
    }

    private fun deleteBasketObserve(item: Product) {
        viewModel.deleteBasketItem(item).observe(viewLifecycleOwner, Observer {
            if (it) Log.d("ProductsFragment", "deleteBasketObserve: ")
            else Log.e("ProductsFragment", "deleteBasketObserve: ")
        })
    }


}