package com.dgr.yourneoweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dgr.yourneoweather.R

class EmptyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_empty, container, false)
}
