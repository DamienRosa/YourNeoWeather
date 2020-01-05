package com.dgr.yourneoweather.ui.adapter

import com.dgr.domain.entity.CityWeather
import com.dgr.yourneoweather.R
import com.dgr.yourneoweather.common.recyclerview.RecyclerViewBaseAdapter

internal class CityAdapter : RecyclerViewBaseAdapter() {

    lateinit var cityList: List<CityWeather>

    override fun getLayoutIdForPosition(position: Int): Int = R.layout.layout_item_city

    override fun getViewModel(position: Int): Any? = cityList[position]

    override fun getItemCount(): Int = cityList.size
}