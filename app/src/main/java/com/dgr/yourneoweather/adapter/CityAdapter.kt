package com.dgr.yourneoweather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dgr.yourneoweather.R
import com.dgr.yourneoweather.mapper.WeatherUIModelMapper
import com.dgr.yourneoweather.model.WeatherUI
import kotlinx.android.synthetic.main.layout_item_city.view.*

internal class CityAdapter(private val mapper: WeatherUIModelMapper) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private lateinit var tempContext: Context
    private var cityList: List<WeatherUI> = emptyList()

    override fun getItemCount(): Int = cityList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        tempContext = parent.context
        val inflater = LayoutInflater.from(parent.context)
        return CityViewHolder(inflater.inflate(R.layout.layout_item_city, parent, false), mapper)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cityList[position]
        holder.bind(city)
        holder.itemView.setOnClickListener {
            onSelectedCity(city)
        }
    }

    lateinit var onSelectedCity : (city: WeatherUI) -> Unit

    class CityViewHolder(itemView: View, mapper: WeatherUIModelMapper) : RecyclerView.ViewHolder(itemView) {
        private val map: WeatherUIModelMapper = mapper
        fun bind(city: WeatherUI) = with(itemView) {
            itemView.tv_city_name.text = city.city
            itemView.tv_city_country.text = city.country
            itemView.tv_temperature.text = city.temperature.toString()
            itemView.tv_humidity.text = context.resources.getString(R.string.ui_humidity, city.humidity)
            itemView.tv_last_update_date.text = context.resources.getString(R.string.ui_last_update_date, city.lastUpdateDate)

            val cardinalImage = map.cardinalDirectionToImage(city.windDirection!!)
            itemView.iv_wind_direction.setBackgroundResource(cardinalImage)

            val weatherImage = map.iconIdToImage(city.weatherIcon ?: -1)
            itemView.iv_weather.setImageResource(weatherImage)

            itemView
        }
    }

    fun setCityList(list: List<WeatherUI>) {
        cityList = list
        notifyDataSetChanged()
    }
}
