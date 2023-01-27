package com.mobi.reachmobi.utils

import android.graphics.Rect
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

inline fun <reified T : ViewModel> initViewModel(): T {
    return ViewModelProvider.NewInstanceFactory().create(T::class.java)
}

fun String?.checkNull(value: String = ""): String {
    return if (this.isNullOrEmpty()) value else this
}

fun getItemDecoration(
    left: Int? = 0,
    top: Int? = 0,
    right: Int? = 0,
    bottom: Int? = 0
): RecyclerView.ItemDecoration {
    return object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.set(left!!, top!!, right!!, bottom!!)
        }
    }
}

fun EditText.getTrimmedText() = this.text.toString().trim()

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString().trim())
        }
    })
}

fun displaySnackBar(view: View) {
    val snackbar = Snackbar
        .make(view, "Please enter valid details", Snackbar.LENGTH_LONG)
        .setAction("OK") {
        }
    snackbar.show()
}

fun View.setGone() {
    visibility = View.GONE
}

fun View.setVisible() {
    visibility = View.VISIBLE
}
