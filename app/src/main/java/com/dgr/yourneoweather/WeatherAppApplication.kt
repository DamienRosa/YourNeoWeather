package com.dgr.yourneoweather

import android.app.Application
import androidx.fragment.app.Fragment
import com.dgr.data.api.OpenWeatherApi
import com.dgr.data.api.helpers.NetworkConnectionInterceptor
import com.dgr.data.api.repository.RemoteWeatherRepository
import com.dgr.data.db.WeatherAppDataBase
import com.dgr.data.db.repository.LocalWeatherRepository
import com.dgr.data.repository.CityWeatherDataRepository
import com.dgr.data.repository.WeatherDataRepository
import com.dgr.domain.repository.CityDomainRepository
import com.dgr.domain.repository.WeatherDomainRepository
import com.dgr.domain.usecase.AddCityUseCase
import com.dgr.domain.usecase.GetCitiesUseCase
import com.dgr.domain.usecase.GetForecastUseCase
import com.dgr.domain.usecase.GetWeatherUseCase
import com.dgr.yourneoweather.adapter.CityAdapter
import com.dgr.yourneoweather.common.ui.BaseViewModelProvider
import com.dgr.yourneoweather.mapper.UIModelMapper
import com.dgr.yourneoweather.ui.home.HomeViewModel
import com.dgr.yourneoweather.ui.searchcity.SearchCityViewModel
import com.dgr.yourneoweather.ui.weatherdetails.WeatherDetailsViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

@Suppress("unused")
class WeatherAppApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@WeatherAppApplication))
        bind() from singleton { WeatherAppDataBase(instance()) }

        bind() from singleton { NetworkConnectionInterceptor() }
        bind() from singleton { OpenWeatherApi(instance()) }

        bind() from singleton { LocalWeatherRepository() }
        bind() from singleton { RemoteWeatherRepository(instance()) }

        bind() from singleton { CityWeatherDataRepository(instance()) }
        bind() from singleton { WeatherDataRepository(instance(), instance()) }

        bind() from singleton { WeatherDomainRepository(instance()) }
        bind() from singleton { CityDomainRepository(instance()) }

        bind() from singleton { GetCitiesUseCase(instance()) }
        bind() from singleton { GetWeatherUseCase(instance()) }
        bind() from singleton { GetForecastUseCase(instance()) }
        bind() from singleton { AddCityUseCase(instance()) }

        bind() from singleton { UIModelMapper() }

        bind() from singleton { CityAdapter(instance()) }

        bind<HomeViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
            BaseViewModelProvider.of(context) { HomeViewModel(instance(), instance()) }
        }

        bind<SearchCityViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
            BaseViewModelProvider.of(context) { SearchCityViewModel(instance(), instance()) }
        }

        bind<WeatherDetailsViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
            BaseViewModelProvider.of(context) { WeatherDetailsViewModel(instance(), instance()) }
        }
    }
}
