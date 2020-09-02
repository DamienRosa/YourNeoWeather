package com.dgr.yourneoweather.common.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard(view: View) {
    (context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
        this.hideSoftInputFromWindow(view.windowToken, 0)
    }
}