package com.dgr.yourneoweather

import android.app.Application
import com.dgr.data.db.WeatherAppDataBase
import com.dgr.data.repository.LocalCityWeatherRepository
import com.dgr.domain.repository.CityRepository
import com.dgr.domain.usecase.GetCitiesUseCase
import com.dgr.yourneoweather.ui.home.HomeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

@Suppress("unused")
class WeatherAppApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@WeatherAppApplication))
        bind() from singleton { WeatherAppDataBase(instance()) }
        bind() from singleton { LocalCityWeatherRepository(instance()) }
        bind() from singleton { CityRepository(instance()) }
        bind() from singleton { GetCitiesUseCase(instance()) }
        bind() from singleton { HomeViewModelFactory(instance()) }
    }
}