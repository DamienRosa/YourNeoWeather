package com.dgr.data.api.model

data class Wind(
    val deg: Int,
    val speed: Double
)

fun Wind.toSpeedDirection() : String =
    when(this.deg){
        0 -> "N"
        else -> "D"
    }
