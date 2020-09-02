package com.dgr.yourneoweather.ui.searchcity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.dgr.yourneoweather.R
import com.dgr.yourneoweather.common.extensions.hideKeyboard
import com.dgr.yourneoweather.common.extensions.observe
import com.dgr.yourneoweather.common.ui.BaseFragment
import com.dgr.yourneoweather.model.WeatherUI
import kotlinx.android.synthetic.main.layout_progress_bar.*
import kotlinx.android.synthetic.main.search_city_fragment.*
import org.kodein.di.generic.instance

class SearchCityFragment : BaseFragment() {

    private val viewModel: SearchCityViewModel by instance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.search_city_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupSearchButton()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.isLoading, ::observerLoading)
        observe(viewModel.isError, ::observerError)
        observe(viewModel.weatherDomain, ::observerWeatherData)
    }

    private fun setupSearchButton() {
        btn_search.setOnClickListener {
            viewModel.getWeather(et_city_name.text.toString())
            hideKeyboard(btn_search)
        }
    }

    private fun observerWeatherData(model: WeatherUI?) {
        if (model == null) return
        et_city_name.text?.clear()
        findNavController().navigate(
            SearchCityFragmentDirections.actionSearchCityFragmentToWeatherDetailsFragment(model)
        )
        viewModel.setModel(null)
    }

    private fun observerError(visibility: Boolean) {
        if (visibility) {
            Toast.makeText(activity, "There was some error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observerLoading(visibility: Boolean) {
        cl_progress_bar.isVisible = visibility
    }
}
