package com.dgr.yourneoweather

import android.app.Application
import androidx.fragment.app.Fragment
import com.dgr.data.api.NetworkConnectionInterceptor
import com.dgr.data.api.OpenWeatherApi
import com.dgr.data.api.RemoteWeatherRepository
import com.dgr.data.db.LocalWeatherRepository
import com.dgr.data.db.WeatherAppDataBase
import com.dgr.data.repository.CityWeatherRepository
import com.dgr.domain.repository.CityRepository
import com.dgr.domain.usecase.GetCitiesUseCase
import com.dgr.domain.usecase.GetForecastUseCase
import com.dgr.domain.usecase.GetWeatherUseCase
import com.dgr.yourneoweather.common.ui.BaseViewModelProvider
import com.dgr.yourneoweather.ui.searchcity.SearchCityViewModel
import com.dgr.yourneoweather.ui.home.HomeViewModel
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

        bind() from singleton { CityWeatherRepository(instance()) }
        bind() from singleton { com.dgr.data.repository.WeatherRepository(instance(), instance()) }

        bind() from singleton { com.dgr.domain.repository.WeatherRepository(instance()) }
        bind() from singleton { CityRepository(instance()) }

        bind() from singleton { GetCitiesUseCase(instance()) }
        bind() from singleton { GetWeatherUseCase(instance()) }
        bind() from singleton { GetForecastUseCase(instance()) }

        bind<HomeViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
            BaseViewModelProvider.of(context) { HomeViewModel(instance()) }
        }

        bind<SearchCityViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
            BaseViewModelProvider.of(context) { SearchCityViewModel(instance()) }
        }
    }
}