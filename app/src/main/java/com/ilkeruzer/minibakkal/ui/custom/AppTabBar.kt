package com.ilkeruzer.minibakkal.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.ilkeruzer.minibakkal.R
import com.ilkeruzer.minibakkal.databinding.CustomAppTabBarLayoutBinding

class AppTabBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: CustomAppTabBarLayoutBinding

    init {
        initLayout()
        setAttr(attrs)
    }

    private fun initLayout() {
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = CustomAppTabBarLayoutBinding.inflate(inflater,this,true)
    }

    private fun setAttr(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.AppTabBar)
        try {
            if (ta.hasValue(R.styleable.AppTabBar_appBar_title)) {
                ta.getString(R.styleable.AppTabBar_appBar_title).also {
                    setTitle(it)
                }
            }
        } finally {
            ta.recycle()
        }
    }

    private fun setTitle(it: String?) {
        binding.titleText.text = it
    }
}