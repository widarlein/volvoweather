package se.widar.volvoweather.data.fakes

import se.widar.volvoweather.currentWeather
import se.widar.volvoweather.data.datasource.CurrentWeatherDataSource
import se.widar.volvoweather.data.model.CurrentWeather

class FakeCurrentWeatherDataSource : CurrentWeatherDataSource {
    var currentWeather: Result<CurrentWeather> = Result.success(currentWeather("Zootropolis"))
    override suspend fun getCurrentWeather(place: String): Result<CurrentWeather> = currentWeather
}