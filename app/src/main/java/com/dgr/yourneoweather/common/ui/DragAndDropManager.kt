package com.dgr.yourneoweather.common.ui

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dgr.yourneoweather.adapter.CityAdapter

class DragAndDropManager(
    adapter: CityAdapter,
    context: Context,
    dragDirection: Int,
    swipeDirection: Int
) : ItemTouchHelper.SimpleCallback(dragDirection, swipeDirection) {

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        TODO("Not yet implemented")
    }
}