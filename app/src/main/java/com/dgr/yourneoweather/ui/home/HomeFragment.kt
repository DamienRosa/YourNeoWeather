package com.dgr.yourneoweather.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dgr.yourneoweather.R
import com.dgr.yourneoweather.adapter.CityAdapter
import com.dgr.yourneoweather.common.extensions.observe
import com.dgr.yourneoweather.common.ui.BaseFragment
import com.dgr.yourneoweather.model.WeatherUI
import com.google.android.material.snackbar.Snackbar
import com.pawegio.kandroid.visible
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_progress_bar.*
import org.kodein.di.generic.instance

class HomeFragment : BaseFragment() {

    private val cAdapter: CityAdapter by instance()

    private val viewModel: HomeViewModel by instance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupList()
        setupFabButton()

        observe(viewModel.cityList(), ::observerCitiesList)
        observe(viewModel.isLoading(), ::observerProgressBar)
        observe(viewModel.isEmpty(), ::observerEmptyList)

        viewModel.loadData()
    }

    private fun setupList() {
        rv_cities.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = cAdapter
        }
    }

    private fun setupFabButton() {
        fb_add_city.setOnClickListener {
            navigateToAddCity()
        }
    }

    private fun observerCitiesList(cities: List<WeatherUI>?) {
        if (cities.isNullOrEmpty()) {
            Snackbar.make(view!!, "", Snackbar.LENGTH_SHORT)
        } else {
            cAdapter.setCityList(cities)
        }
    }

    private fun observerEmptyList(visibility: Boolean?) {
        ll_messaging_container.visible = visibility ?: false
    }

    private fun observerProgressBar(visibility: Boolean?) {
        cl_progress_bar.visible = visibility ?: false
    }

    private fun navigateToAddCity() {
        findNavController().navigate(HomeFragmentDirections.actionMenuHomeToSearchCityFragment())
    }
}
