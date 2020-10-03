package com.ilkeruzer.minibakkal.ui.fragment.basket

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ilkeruzer.minibakkal.IBaseListener.AppBarBasketListener
import com.ilkeruzer.minibakkal.databinding.BasketFragmentBinding
import com.ilkeruzer.minibakkal.ui.fragment.BaseFragment
import org.koin.android.ext.android.inject

class BasketFragment : BaseFragment<BasketViewModel>(), AppBarBasketListener {

    private val vM by inject<BasketViewModel>()
    private lateinit var binding: BasketFragmentBinding

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
    }

    override fun deleteClick() {
        Log.d("BasketFragment", "deleteClick: ")
    }

    override fun closeClick() {
        findNavController().popBackStack()
    }


}