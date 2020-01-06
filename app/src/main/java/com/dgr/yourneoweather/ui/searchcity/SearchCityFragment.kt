package com.dgr.yourneoweather.ui.searchcity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dgr.yourneoweather.R
import com.dgr.yourneoweather.common.extensions.observe
import com.dgr.yourneoweather.common.ui.BaseFragment
import com.google.android.material.snackbar.Snackbar
import com.pawegio.kandroid.visible
import kotlinx.android.synthetic.main.layout_progress_bar.*
import kotlinx.android.synthetic.main.search_city_fragment.*
import org.kodein.di.android.subKodein
import org.kodein.di.generic.instance

class SearchCityFragment : BaseFragment() {

    private val viewModel: SearchCityViewModel by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_city_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupSearchButton()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.viewState, Observer {
            cl_progress_bar.visible = it.isLoading
            if(!it.isLoading) {
                if (it.isError) {
                    Snackbar.make(view, "There was some error", Snackbar.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context!!.applicationContext, "Navigate to details of " +
                            it.weatherData?.city, Toast.LENGTH_SHORT).show()
//                    findNavController().navigate(SearchCityFragmentDirections.actionSearchCityFragmentToWeatherDetailsFragment())
                }
            }
        })
    }

    private fun setupSearchButton() {
        btn_search.setOnClickListener {
            viewModel.getWeather(et_city_name.text.toString())

        }
    }

}
