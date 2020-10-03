package com.ilkeruzer.minibakkal.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import com.ilkeruzer.minibakkal.R

class ProgressDialog(context: Context?) : Dialog(context!!) {
    init {
        val window = window
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window.requestFeature(Window.FEATURE_NO_TITLE)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        window.attributes = lp
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        setContentView(R.layout.circular_progress)
    }
}