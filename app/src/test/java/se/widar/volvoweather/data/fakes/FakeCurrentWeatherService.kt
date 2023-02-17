package se.widar.volvoweather.data.fakes

import retrofit2.Response
import se.widar.volvoweather.currentWeather
import se.widar.volvoweather.data.datasource.client.CurrentWeatherService
import se.widar.volvoweather.data.model.CurrentWeather

class FakeCurrentWeatherService : CurrentWeatherService {
    override suspend fun getCurrentWeather(
        lat: Double,
        long: Double,
        apiKey: String,
        units: String
    ): Response<CurrentWeather> {
        throw UnsupportedOperationException("Not actually using this in the code base but kept in anyway, so don't include it in tests")
    }

    var currentWeather: Response<CurrentWeather> = Response.success(currentWeather("DunBroch"))
    var exception: Exception? = null
    override suspend fun getCurrentWeather(
        query: String,
        apiKey: String,
        units: String
    ): Response<CurrentWeather> {
        val exception = exception
        if (exception != null) {
            throw exception
        }
        return currentWeather
    }
}