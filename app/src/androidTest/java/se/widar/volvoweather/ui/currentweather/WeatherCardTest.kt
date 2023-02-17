package se.widar.volvoweather.ui.currentweather

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import se.widar.volvoweather.data.model.*

class WeatherCardTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun whenWeather_showCard() {
        val city = "Gothenburg"
        val weather = "rain"
        val description = "light rain"
        rule.setContent {
            WeatherCard(currentWeather = currentWeather(city, weather, description))
        }

        rule.onNodeWithText(city).assertExists()
        rule.onNodeWithText(description).assertExists()
    }
}

fun currentWeather(name: String, weatherMain: String, weatherDescription: String) = CurrentWeather(
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
        Weather(id = 0, icon = "0d", description = weatherDescription, main = weatherMain)
    ),
    wind = Wind(deg = 0, gust = 0.0, speed = 0.0),
)