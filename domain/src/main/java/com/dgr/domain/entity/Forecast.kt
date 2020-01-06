package com.dgr.domain.entity

data class Forecast(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val snow: Snow,
    val sys: SysX,
    val weather: List<Weather>,
    val wind: Wind
)