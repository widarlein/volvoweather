package se.widar.volvoweather.ui.currentweather

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Rule
import org.junit.Test
import se.widar.volvoweather.MainDispatcherRule
import se.widar.volvoweather.currentWeathers
import se.widar.volvoweather.data.fakes.FakeCitiesWeatherRepository
import se.widar.volvoweather.data.model.*
import se.widar.volvoweather.data.model.exceptions.UnsuccessfulRequestException

@OptIn(ExperimentalCoroutinesApi::class)
class CurrentWeatherViewModelTest {

    private val citiesWeatherRepository = FakeCitiesWeatherRepository()
    private lateinit var underTest: CurrentWeatherViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun givenEmptyResult_whenGetCurrentWeather_thenNoChange() = runTest {
        citiesWeatherRepository.weatherForCities = listOf()
        val currentWeathers = underTest.currentWeathers
        assertThat(currentWeathers).isEmpty()
    }

    @Test
    fun givenAllSuccessResult_whenGetCurrentWeather_thenFullCurrentWeathers() = runTest {
        val actualCurrentWeathers = currentWeathers()
        citiesWeatherRepository.weatherForCities = actualCurrentWeathers.map { Result.success(it) }

        underTest = CurrentWeatherViewModel(citiesWeatherRepository)

        val currentWeathers = underTest.currentWeathers

        assertThat(currentWeathers).containsExactlyElementsIn(actualCurrentWeathers)
        assertThat(underTest.errorCities).isEmpty()
    }

    @Test
    fun givenAllFailResult_whenGetCurrentWeather_thenEmptyCurrentWeathers() = runTest {
        val actualCurrentWeathers = currentWeathers()
        citiesWeatherRepository.weatherForCities = actualCurrentWeathers.map {
            Result.failure(
                UnsuccessfulRequestException(
                    "message",
                    requestSubject = it.name
                )
            )
        }

        underTest = CurrentWeatherViewModel(citiesWeatherRepository)

        val currentWeathers = underTest.currentWeathers

        assertThat(currentWeathers).isEmpty()
        assertThat(underTest.errorCities).containsExactlyElementsIn(actualCurrentWeathers.map { it.name })
    }
}