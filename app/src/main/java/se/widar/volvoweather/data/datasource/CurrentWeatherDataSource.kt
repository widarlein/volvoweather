package se.widar.volvoweather.data.datasource

import se.widar.volvoweather.data.datasource.client.CurrentWeatherService
import se.widar.volvoweather.data.di.ApiKey
import se.widar.volvoweather.data.model.CurrentWeather
import se.widar.volvoweather.data.model.exceptions.UnsuccessfulRequestException
import java.io.IOException
import javax.inject.Inject

interface CurrentWeatherDataSource {
    suspend fun getCurrentWeather(place: String): Result<CurrentWeather>
}

class CurrentWeatherDataSourceImpl @Inject constructor(
    private val currentWeatherService: CurrentWeatherService,
    @ApiKey private val apiKey: String
) : CurrentWeatherDataSource {
    override suspend fun getCurrentWeather(place: String): Result<CurrentWeather> {
        return try {

            val response = currentWeatherService.getCurrentWeather(
                query = place,
                apiKey = apiKey
            )
            val body = response.body()

            if (response.isSuccessful && body != null) {
                Result.success(body)
            } else {
                Result.failure(UnsuccessfulRequestException(response.message(), requestSubject = place))
            }
        } catch (e: Exception) {
            when (e) {
                is IOException, is RuntimeException -> Result.failure(e)
                else -> throw (e)
            }

        }

    }

}