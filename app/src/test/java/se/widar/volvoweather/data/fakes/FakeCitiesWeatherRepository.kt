package se.widar.volvoweather.data.fakes

import se.widar.volvoweather.data.model.CurrentWeather
import se.widar.volvoweather.data.repository.CitiesWeatherRepository

class FakeCitiesWeatherRepository : CitiesWeatherRepository {

    var weatherForCities: List<Result<CurrentWeather>> = listOf()
    override suspend fun getWeatherForCities(cities: List<String>): List<Result<CurrentWeather>> = weatherForCities
}