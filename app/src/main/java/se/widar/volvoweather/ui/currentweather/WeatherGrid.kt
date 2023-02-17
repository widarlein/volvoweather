package se.widar.volvoweather.ui.currentweather

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import se.widar.volvoweather.data.model.CurrentWeather

@Composable
fun WeatherGrid(
    items: List<CurrentWeather>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        modifier = modifier
    ) {
        items(items) { currentWeather ->
            WeatherCard(
                currentWeather = currentWeather,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}