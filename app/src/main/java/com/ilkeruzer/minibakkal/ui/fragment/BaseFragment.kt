package com.ilkeruzer.minibakkal.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ilkeruzer.minibakkal.ui.BaseViewModel
import com.ilkeruzer.minibakkal.util.ProgressDialog
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

abstract class BaseFragment<VM : BaseViewModel>() : Fragment() {

    private val TAG = "BaseFragment"
    protected lateinit var viewModel: VM
    private var dialog: ProgressDialog? = null
    private var wheelingProcessDialogScheduler: ScheduledExecutorService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        equalsViewModel(savedInstanceState)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return getViewBindingRoot(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unSubscribeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.unSubscribeViewModel()
    }

    open fun showLoading() {
        if (requireActivity().isFinishing) {
            return
        }
        if (dialog == null || !(dialog!!.isShowing && requireActivity().window.decorView.isShown)) {
            dialog = ProgressDialog(activity)
            dialog!!.show()
            startWheelingProcessDialogScheduler(dialog)
        }
    }

    open fun stopLoading() {
        if (requireActivity().isFinishing) {
            return
        }
        if (dialog != null) {
            dialog!!.dismiss()
            dialog = null
        }
        stopWheelingProcessDialogScheduler()
    }

    private fun startWheelingProcessDialogScheduler(d: ProgressDialog?) {
        if (wheelingProcessDialogScheduler != null && !wheelingProcessDialogScheduler!!.isShutdown) {
            stopWheelingProcessDialogScheduler()
        }
        wheelingProcessDialogScheduler = Executors.newSingleThreadScheduledExecutor()
        wheelingProcessDialogScheduler!!.schedule({
            requireActivity().runOnUiThread {
                try {
                    if (d != null && d.isShowing) {
                        d.dismiss()
                    }
                } catch (e: Throwable) {
                    Log.e(TAG, "Progress dialog scheduler error ", e)
                }
            }
        }, 20.toLong(), TimeUnit.SECONDS)
    }

    private fun stopWheelingProcessDialogScheduler() {
        try {
            if (wheelingProcessDialogScheduler != null) {
                wheelingProcessDialogScheduler!!.shutdownNow()
                wheelingProcessDialogScheduler = null
            }
        } catch (e: Throwable) {
            Log.e(TAG, e.message, e)
        }
    }

    protected abstract fun equalsViewModel(savedInstanceState: Bundle?)
    protected abstract fun getViewBindingRoot(inflater: LayoutInflater, container: ViewGroup?): View?
    protected abstract fun getViewModel(): Class<VM>
    protected abstract fun viewCreated(view: View, savedInstanceState: Bundle?)
}