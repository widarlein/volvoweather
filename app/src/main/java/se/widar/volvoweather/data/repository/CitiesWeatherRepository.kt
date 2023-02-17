package se.widar.volvoweather.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import se.widar.volvoweather.data.datasource.CurrentWeatherDataSource
import se.widar.volvoweather.data.di.Io
import se.widar.volvoweather.data.model.CurrentWeather
import javax.inject.Inject

interface CitiesWeatherRepository {
    suspend fun getWeatherForCities(cities: List<String>): List<Result<CurrentWeather>>
}

class CitiesWeatherRepositoryImpl @Inject constructor(
    private val dataSource: CurrentWeatherDataSource,
    @Io private val dispatcher: CoroutineDispatcher
) : CitiesWeatherRepository {

    override suspend fun getWeatherForCities(cities: List<String>): List<Result<CurrentWeather>> {
        return withContext(dispatcher) {
            val deferreds = cities.map { city ->
                async {
                    dataSource.getCurrentWeather(city)
                }
            }
            deferreds.awaitAll()
        }
    }

}