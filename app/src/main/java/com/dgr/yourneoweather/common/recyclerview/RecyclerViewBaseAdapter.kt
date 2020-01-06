package com.dgr.yourneoweather.common.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.dgr.yourneoweather.BR
import java.lang.IllegalStateException

abstract class RecyclerViewBaseAdapter : RecyclerView.Adapter<RecyclerBaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerBaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, viewType, parent, false)
        return RecyclerBaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerBaseViewHolder, position: Int) {
        getViewModel(position)?.let {
            val bindingSuccess = holder.binding.setVariable(BR.viewModel, it)
            if (!bindingSuccess) {
                throw IllegalStateException("Binding is not viewModel")
            }
        }
    }

    override fun getItemViewType(position: Int): Int = getLayoutIdForPosition(position)

    abstract fun getLayoutIdForPosition(position: Int): Int

    abstract fun getViewModel(position: Int): Any?
}