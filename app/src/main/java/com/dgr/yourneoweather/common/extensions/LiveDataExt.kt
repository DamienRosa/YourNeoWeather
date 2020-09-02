package com.dgr.yourneoweather.common.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, body: (T) -> Unit) {
    liveData.observe(this, Observer(body))
}

fun <T> Fragment.observe(liveData: LiveData<T>, body: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer(body))
}
