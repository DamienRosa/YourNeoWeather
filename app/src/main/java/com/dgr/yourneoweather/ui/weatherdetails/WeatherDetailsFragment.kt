package com.dgr.yourneoweather.ui.weatherdetails

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs

import com.dgr.yourneoweather.R

class WeatherDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherDetailsFragment()
    }

    private lateinit var viewModel: WeatherDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WeatherDetailsViewModel::class.java)

        // TODO: Use the ViewModel
    }

}
