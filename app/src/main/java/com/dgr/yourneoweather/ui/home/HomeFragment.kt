package com.dgr.yourneoweather.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dgr.yourneoweather.R
import com.dgr.yourneoweather.common.extensions.observe
import com.dgr.yourneoweather.common.ui.BaseFragment
import com.dgr.yourneoweather.ui.adapter.CityAdapter
import com.pawegio.kandroid.visible
import kotlinx.android.synthetic.main.fragment_home.*
import org.kodein.di.generic.instance

class HomeFragment : BaseFragment() {

    private val cAdapter: CityAdapter by lazy { CityAdapter() }

    private val factory: HomeViewModelFactory by instance()

    private val viewModel: HomeViewModel by lazy { ViewModelProviders.of(this, factory).get(HomeViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupList()
        setupFabButton()

        observe(viewModel.viewState, Observer {
            cAdapter.cityList = it.citiesList
            pb_loading.visible = it.isLoading
            ll_messaging_container.visible = it.isError
        })

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

    private fun navigateToAddCity() {
        Toast.makeText(context, "UNDER CONSTRUCTION", Toast.LENGTH_SHORT).show()
    }
}

