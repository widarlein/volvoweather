package se.widar.volvoweather.ui.currentweather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import se.widar.volvoweather.R
import se.widar.volvoweather.data.model.*

@Composable
fun WeatherCard(
    currentWeather: CurrentWeather,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val weather = currentWeather.weather.first()
                AsyncImage(
                    model = "https://openweathermap.org/img/wn/${weather.icon}@2x.png",
                    contentDescription = weather.description,
                    modifier = Modifier.size(64.dp)
                )
                Text(weather.description, fontSize = 14.sp, overflow = TextOverflow.Ellipsis)

                Spacer(modifier = Modifier.size(8.dp))

                Text("${String.format("%.1f", currentWeather.main.temp)}${stringResource(R.string.weather_temp_suffix)}", fontSize = 14.sp, fontWeight = FontWeight.Bold, overflow = TextOverflow.Ellipsis)
                Text(stringResource(R.string.weather_humidity_template, currentWeather.main.humidity), fontSize = 14.sp, fontWeight = FontWeight.Bold, overflow = TextOverflow.Ellipsis)
            }
            Text(currentWeather.name, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@Preview
@Composable
fun WeatherCardPreview() {
    WeatherCard(
        currentWeather = CurrentWeather(
            base = "base",
            clouds = Clouds(all = 0),
            cod = 0,
            coord = Coord(lat = 0.0, lon = 0.0),
            dt = 0,
            id = 0,
            main = Main(
                feelsLike = 0.0,
                humidity = 57,
                pressure = 0,
                temp = 10.0,
                tempMax = 0.0,
                tempMin = 0.0,
                grndLevel = 0,
                seaLevel = 0
            ),
            name = "Gothenburg",
            rain = null,
            sys = Sys(type = 0, country = "SE", id = 0, sunrise = 0, sunset = 0),
            timezone = 0,
            visibility = 0,
            weather = listOf(
                Weather(id = 0, icon = "10d", description = "light rain", main = "rain")
            ),
            wind = Wind(deg = 0, gust = 0.0, speed = 0.0),
        )
    )
}