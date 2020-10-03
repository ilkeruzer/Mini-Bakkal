package com.ilkeruzer.minibakkal.ui.fragment.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ilkeruzer.minibakkal.databinding.ProductsFragmentBinding
import com.ilkeruzer.minibakkal.ui.fragment.BaseFragment
import org.koin.android.ext.android.inject

class ProductsFragment : BaseFragment<ProductsViewModel>() {

    private val vM by inject<ProductsViewModel>()
    private lateinit var binding: ProductsFragmentBinding

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

    }


}