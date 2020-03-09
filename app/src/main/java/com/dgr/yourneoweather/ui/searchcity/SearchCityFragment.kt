package com.dgr.yourneoweather.ui.searchcity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dgr.domain.entity.WeatherDomain
import com.dgr.yourneoweather.R
import com.dgr.yourneoweather.common.model.WeatherUI
import com.dgr.yourneoweather.common.extensions.observe
import com.dgr.yourneoweather.common.ui.BaseFragment
import com.google.android.material.snackbar.Snackbar
import com.pawegio.kandroid.inputMethodManager
import com.pawegio.kandroid.visible
import kotlinx.android.synthetic.main.layout_progress_bar.*
import kotlinx.android.synthetic.main.search_city_fragment.*
import org.kodein.di.generic.instance

class SearchCityFragment : BaseFragment() {

    private val viewModel: SearchCityViewModel by instance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.search_city_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupSearchButton()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.isLoading, observerLoading())
        observe(viewModel.isError, observerError(view))
        observe(viewModel.weatherDomain, observerWeatherData())
    }

    private fun setupSearchButton() {
        btn_search.setOnClickListener {
            viewModel.getWeather(et_city_name.text.toString())
            context!!.inputMethodManager!!.hideSoftInputFromWindow(btn_search.windowToken, 0)
        }
    }

    private fun observerWeatherData(): Observer<WeatherDomain> = Observer {
        if (it != null) {
            findNavController().navigate(
                SearchCityFragmentDirections.actionSearchCityFragmentToWeatherDetailsFragment(it.toParcelModel())
            )
            viewModel.setModel(null)
        }
    }

    private fun observerError(view: View): Observer<Boolean> = Observer {
        if (it) {
            Snackbar.make(view, "There was some error", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun observerLoading(): Observer<Boolean> = Observer {
        cl_progress_bar.visible = it
    }

    private fun WeatherDomain.toParcelModel(): WeatherUI =
        WeatherUI(
            city = this.city,
            country = this.country,
            description = this.description,
            windSpeed = this.windSpeed,
            windDirection = this.windDirection,
            temperature = this.temperature,
            humidity = this.humidity,
            pressure = this.pressure,
            visibility = this.visibility,
            sunrise = this.sunrise,
            sunset = this.sunset,
            weatherIcon = this.weatherIcon,
            lastUpdateDate = this.lastUpdateDate
        )
}
