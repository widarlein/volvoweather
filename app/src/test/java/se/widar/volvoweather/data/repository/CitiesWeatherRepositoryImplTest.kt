package se.widar.volvoweather.data.repository

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import se.widar.volvoweather.MainDispatcherRule
import se.widar.volvoweather.data.fakes.FakeCurrentWeatherDataSource

@OptIn(ExperimentalCoroutinesApi::class)
class CitiesWeatherRepositoryImplTest {

    private val dataSource = FakeCurrentWeatherDataSource()

    lateinit var underTest: CitiesWeatherRepositoryImpl

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        underTest = CitiesWeatherRepositoryImpl(dataSource, mainDispatcherRule.testDispatcher)
    }

    @Test
    fun givenNoCities_whenGetWeather_thenEmptyList() = runTest {
        val weathers = underTest.getWeatherForCities(listOf())
        assertThat(weathers).isEmpty()
    }

    @Test
    fun givenCities_whenGetWeather_thenSameNumberOfCities() = runTest {
        val cities = listOf("Metroville", "Radiator Springs")
        val weathers = underTest.getWeatherForCities(cities)
        assertThat(weathers).hasSize(cities.size)
    }
}