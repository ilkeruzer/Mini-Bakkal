package com.ilkeruzer.minibakkal.ui.fragment.basket

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.ilkeruzer.minibakkal.IBaseListener
import com.ilkeruzer.minibakkal.IBaseListener.AppBarBasketListener
import com.ilkeruzer.minibakkal.data.getProductMockList
import com.ilkeruzer.minibakkal.databinding.BasketFragmentBinding
import com.ilkeruzer.minibakkal.model.Product
import com.ilkeruzer.minibakkal.ui.adapter.BasketAdapter
import com.ilkeruzer.minibakkal.ui.fragment.BaseFragment
import org.koin.android.ext.android.inject

class BasketFragment : BaseFragment<BasketViewModel>(), AppBarBasketListener,
    IBaseListener.ProductItemListener<Product> {

    private val vM by inject<BasketViewModel>()
    private lateinit var binding: BasketFragmentBinding
    private val basketAdapter by inject<BasketAdapter>()

    override fun equalsViewModel(savedInstanceState: Bundle?) {
        viewModel = vM
    }

    override fun getViewBindingRoot(inflater: LayoutInflater, container: ViewGroup?): View? {
        binding = BasketFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getViewModel(): Class<BasketViewModel> {
        return BasketViewModel::class.java
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        binding.appTabBar.setBasketListener(this)
        initRecycler()
    }

    private fun initRecycler() {
        binding.recList.apply {
            setRecyclerView(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = basketAdapter
        }

        basketAdapter.setProductListener(this)

        basketAdapter.notifyReload(getProductMockList())
    }

    override fun deleteClick() {
        Log.d("BasketFragment", "deleteClick: ")
    }

    override fun closeClick() {
        findNavController().popBackStack()
    }

    override fun addBasket(item: Product, position: Int) {
        Log.d("BasketFragment", "addBasket: ")
    }

    override fun removeBasket(item: Product, position: Int) {
        Log.d("BasketFragment", "removeBasket: ")
    }


}