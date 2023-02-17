package se.widar.volvoweather.data.datasource.client

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import se.widar.volvoweather.data.model.CurrentWeather

interface CurrentWeatherService {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Response<CurrentWeather>

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") query: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Response<CurrentWeather>
}