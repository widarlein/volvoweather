package se.widar.volvoweather.ui.currentweather

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import se.widar.volvoweather.R

@Composable
fun CurrentWeatherScreen(
    modifier: Modifier = Modifier,
    weatherViewModel: CurrentWeatherViewModel = viewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Scaffold(modifier = modifier, scaffoldState = scaffoldState,
        topBar = {
            TopAppBar {
                Text(stringResource(R.string.app_title))
            }
        }
    ) { padding ->
        WeatherGrid(
            items = weatherViewModel.currentWeathers,
            modifier = Modifier.padding(padding.calculateTopPadding())
        )

        if (weatherViewModel.errorCities.isNotEmpty()) {
            val errorString = stringResource(R.string.snackbar_error_text, weatherViewModel.errorCities.joinToString())
            LaunchedEffect(scaffoldState.snackbarHostState) {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = errorString,
                )
            }
        }
    }

}