package se.widar.volvoweather

import se.widar.volvoweather.data.model.*

fun currentWeathers() = listOf(
    currentWeather("Ankeborg"),
    currentWeather("Metrocity")
)

fun currentWeather(name: String) = CurrentWeather(
    base = "base",
    clouds = Clouds(all = 0),
    cod = 0,
    coord = Coord(lat = 0.0, lon = 0.0),
    dt = 0,
    id = 0,
    main = Main(
        feelsLike = 0.0,
        humidity = 0,
        pressure = 0,
        temp = 0.0,
        tempMax = 0.0,
        tempMin = 0.0,
        grndLevel = 0,
        seaLevel = 0
    ),
    name = name,
    rain = null,
    sys = Sys(type = 0, country = "SE", id = 0, sunrise = 0, sunset = 0),
    timezone = 0,
    visibility = 0,
    weather = listOf(
        Weather(id = 0, icon = "0d", description = "rain", main = "rain")
    ),
    wind = Wind(deg = 0, gust = 0.0, speed = 0.0),
)