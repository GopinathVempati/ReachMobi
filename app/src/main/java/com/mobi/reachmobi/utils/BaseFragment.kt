package com.mobi.reachmobi.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mobi.reachmobi.R
import kotlinx.android.synthetic.main.loading_layout.*

abstract class BaseFragment : Fragment() {
    private var context1: AppCompatActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context1 = (requireActivity() as BaseActivity)
    }

    fun showProgressBar() {
        if(isAdded) {
            progressLayout?.setVisible()
            context1?.window?.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        }
    }

    fun hideProgressBar() {
        if(isAdded) {
            progressLayout?.setGone()
            context1?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    fun apiCall(
        defaultMsg: String = getString(R.string.required_internet),
        networkFun: () -> Unit,
    ) {
        (requireActivity() as BaseActivity).apiCall(defaultMsg, networkFun)
    }

    fun hideKeyboard() {
        val view = requireActivity().currentFocus
        if (view != null) {
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}