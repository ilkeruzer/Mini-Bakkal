package com.ilkeruzer.minibakkal.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.ilkeruzer.minibakkal.IBaseListener.BasketStatusListener
import com.ilkeruzer.minibakkal.R
import com.ilkeruzer.minibakkal.databinding.CustomBasketStatusLayoutBinding

class BasketStatus @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: CustomBasketStatusLayoutBinding
    private var listener: BasketStatusListener? = null

    init {
        initLayout()
        setAttr(attrs)
        clickListener()
    }

    private fun initLayout() {
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = CustomBasketStatusLayoutBinding.inflate(inflater, this, true)
    }

    private fun setAttr(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.BasketStatus)
        try {

            if (ta.hasValue(R.styleable.BasketStatus_basket_sum)) {
                ta.getString(R.styleable.BasketStatus_basket_sum).also {
                    setSumText(it)
                }
            }

        } finally {
            ta.recycle()
        }
    }

    private fun setSumText(sum: String?) {
        sum.let { binding.sumText.text = it }
    }

    private fun clickListener() {
        binding.confirmBtn.setOnClickListener { listener?.confirmClick() }
    }

    fun setListener(listener: BasketStatusListener) {
        this.listener = listener
    }
}