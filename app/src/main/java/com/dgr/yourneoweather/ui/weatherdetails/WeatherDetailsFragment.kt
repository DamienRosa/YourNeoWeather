package com.dgr.yourneoweather.ui.weatherdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.dgr.yourneoweather.R
import com.dgr.yourneoweather.common.extensions.observe
import com.dgr.yourneoweather.common.ui.BaseFragment
import com.dgr.yourneoweather.model.WeatherUI
import kotlinx.android.synthetic.main.weather_details_fragment.*
import org.kodein.di.generic.instance

class WeatherDetailsFragment : BaseFragment() {

    private lateinit var weatherDetails: WeatherUI

    private val viewModel: WeatherDetailsViewModel by instance()

    private val args: WeatherDetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.weather_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        weatherDetails = args.weatherDetails
        setSetupView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    private fun setObservers() {
        observe(viewModel.weatherDetails) {
            weatherDetails = it
            setSetupView()
        }
    }

    private fun setSetupView() {
        tv_last_update_date.text = weatherDetails.lastUpdateDate
        tv_city_name.text = weatherDetails.city
        tv_temperature.text = weatherDetails.temperature.toString()
        iv_refresh_data.setOnClickListener {
            viewModel.getWeather(weatherDetails.city)
        }

        btn_save.setOnClickListener {
            viewModel.saveCityWeather(weatherDetails)
        }
    }
}
