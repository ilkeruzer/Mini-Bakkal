package com.ilkeruzer.minibakkal.ui.fragment.basket

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.ilkeruzer.minibakkal.IBaseListener.*
import com.ilkeruzer.minibakkal.R
import com.ilkeruzer.minibakkal.databinding.BasketFragmentBinding
import com.ilkeruzer.minibakkal.model.Product
import com.ilkeruzer.minibakkal.ui.adapter.BasketAdapter
import com.ilkeruzer.minibakkal.ui.fragment.BaseFragment
import com.ilkeruzer.minibakkal.util.AppUtil
import org.koin.android.ext.android.inject

class BasketFragment : BaseFragment<BasketViewModel>(), AppBarBasketListener,
    ProductItemListener<Product> {

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
        basketObserve()
        basketSumPriceObserve()
    }

    private fun basketSumPriceObserve() {
        viewModel.getBasketSumPriceLiveData().observe(viewLifecycleOwner, {
            binding.basketStatus.setSumText(
                getString(R.string.tl) +
                AppUtil.doubleToDecimal(it).toString()
            )
        })
    }

    private fun basketObserve() {
        viewModel.getAllBasket().observe(viewLifecycleOwner, {
            if (it != null && it.size > 0) {
                binding.basketStatus.visibility = View.VISIBLE
            } else {
                binding.basketStatus.visibility = View.GONE
            }
            basketAdapter.notifyReload(it)
        })
    }

    private fun initRecycler() {
        binding.recList.apply {
            setRecyclerView(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = basketAdapter
        }
        basketAdapter.setProductListener(this)
    }

    override fun deleteClick() {
        Log.d("BasketFragment", "deleteClick: ")
        showAlertDialog(getString(R.string.Warning),
            getString(R.string.drop_basket),
            getString(R.string.yes),
            getString(R.string.no),
            object : AlertDialogButtonListener {
                override fun positiveClick() {
                    dropBasketObserve()
                }

                override fun negativeClick() {}
            })
    }

    private fun dropBasketObserve() {
        viewModel.dropTable().observe(viewLifecycleOwner, Observer {
            if (it)
                findNavController().popBackStack()
        })
    }

    override fun closeClick() {
        findNavController().popBackStack()
    }

    override fun addBasket(item: Product, position: Int) {
        if (item.basket != item.stock) {
            if (item.basket == 0) {
                item.basket += 1
                saveBasketObserve(item)
            } else {
                item.basket += 1
                updateBasketObserve(item)
            }

            basketAdapter.updateItem(position, item)
        }
    }

    override fun removeBasket(item: Product, position: Int) {
        Log.d("BasketFragment", "removeBasket: ")
        item.basket -= 1
        if (item.basket == 0) deleteBasketObserve(item)
        else updateBasketObserve(item)
        basketAdapter.updateItem(position, item)
    }

    private fun updateBasketObserve(item: Product) {
        Log.d("BasketFragment", "updateBasketObserve: ")
        viewModel.updateBasketItem(item).observe(viewLifecycleOwner, Observer {
            if (it) Log.d("BasketFragment", "updateBasketObserve: ")
            else Log.e("BasketFragment", "updateBasketObserve: ")
        })
    }

    private fun saveBasketObserve(item: Product) {
        viewModel.saveBasket(item).observe(viewLifecycleOwner, {
            if (it) Log.d("BasketFragment", "saveBasketObserve: ")
            else Log.e("BasketFragment", "saveBasketObserve: ")
        })
    }

    private fun deleteBasketObserve(item: Product) {
        viewModel.deleteBasketItem(item).observe(viewLifecycleOwner, Observer {

        })
    }

}