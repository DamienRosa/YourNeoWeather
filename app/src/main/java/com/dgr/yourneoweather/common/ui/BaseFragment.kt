package com.dgr.yourneoweather.common.ui

import androidx.fragment.app.Fragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein

open class BaseFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
}
