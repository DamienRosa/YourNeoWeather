package com.dgr.yourneoweather.common.ui

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dgr.yourneoweather.adapter.CityAdapter

class DragAndDropManager(
    private val adapter: CityAdapter,
    dragDirection: Int,
    swipeDirection: Int
) : ItemTouchHelper.SimpleCallback(dragDirection, swipeDirection) {

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean =
        adapter.swapItems(viewHolder.adapterPosition, target.adapterPosition).let { true }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.removeItem(viewHolder.adapterPosition)
    }
}