package com.dgr.yourneoweather.ui.adapter

import com.dgr.domain.entity.WeatherDomain
import com.dgr.yourneoweather.R
import com.dgr.yourneoweather.common.recyclerview.RecyclerViewBaseAdapter

internal class CityAdapter : RecyclerViewBaseAdapter() {

    lateinit var cityList: List<WeatherDomain>

    override fun getLayoutIdForPosition(position: Int): Int = R.layout.layout_item_city

    override fun getViewModel(position: Int): WeatherDomain? = cityList[position]

    override fun getItemCount(): Int = cityList.size
}