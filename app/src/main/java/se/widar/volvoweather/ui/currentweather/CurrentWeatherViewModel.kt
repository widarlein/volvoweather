package se.widar.volvoweather.ui.currentweather

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import se.widar.volvoweather.data.model.CurrentWeather
import se.widar.volvoweather.data.model.exceptions.UnsuccessfulRequestException
import se.widar.volvoweather.data.repository.CitiesWeatherRepository
import javax.inject.Inject

val CITIES = listOf("Gothenburg", "Stockholm", "Mountain View", "London", "New York", "Berlin")

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val citiesWeatherRepository: CitiesWeatherRepository
) : ViewModel() {

    private val _currentWeathers by lazy {
        mutableStateOf(listOf<CurrentWeather>())
    }

    val currentWeathers: List<CurrentWeather>
        get() = _currentWeathers.value

    var errorCities by mutableStateOf(listOf<String>())

    init {
        loadWeather()
    }

    private fun loadWeather() {
        viewModelScope.launch {
            val weathers = citiesWeatherRepository.getWeatherForCities(cities = CITIES)
            if (weathers.isNotEmpty()) {
                val successCities = weathers.filter { it.isSuccess }.mapNotNull { it.getOrNull() }
                val failCities = weathers.filter { it.isFailure }.mapNotNull { r ->
                    r.exceptionOrNull()
                        ?.let { if (it is UnsuccessfulRequestException) it.requestSubject else null }
                }

                _currentWeathers.value = successCities
                errorCities = failCities
            }

        }

    }

}