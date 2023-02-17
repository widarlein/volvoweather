package se.widar.volvoweather.data.datasource

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import se.widar.volvoweather.MainDispatcherRule
import se.widar.volvoweather.currentWeather
import se.widar.volvoweather.data.fakes.FakeCurrentWeatherService
import se.widar.volvoweather.data.model.exceptions.UnsuccessfulRequestException
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class CurrentWeatherDataSourceImplTest {

    private val currentWeatherService = FakeCurrentWeatherService()

    lateinit var underTest: CurrentWeatherDataSourceImpl

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        underTest =
            CurrentWeatherDataSourceImpl(currentWeatherService = currentWeatherService, apiKey = "")
    }

    @Test
    fun whenGetWeatherSuccess_thenSuccessResult() = runTest {
        val currentWeather = currentWeather("Santa Cecilia")
        currentWeatherService.currentWeather = Response.success(currentWeather)

        val result = underTest.getCurrentWeather("Coco City")

        assertThat(result.isSuccess).isEqualTo(true)
        assertThat(result.getOrNull()).isEqualTo(currentWeather)
    }

    @Test
    fun whenGetWeatherFailure_thenFailureResult() = runTest {
        currentWeatherService.currentWeather = Response.error(401, "Could not reach Duloc".toResponseBody())

        val place = "Farquad City"
        val result = underTest.getCurrentWeather(place)
        val throwable = result.exceptionOrNull()

        assertThat(result.isFailure).isEqualTo(true)
        assertThat(throwable).isNotNull()
        assertThat(throwable).isInstanceOf(UnsuccessfulRequestException::class.java)
        assertThat((throwable as UnsuccessfulRequestException).requestSubject).isEqualTo(place)
    }

    @Test
    fun whenGetWeatherException_thenFailureResult() = runTest {
        currentWeatherService.exception = IOException("Internet bad")

        val result = underTest.getCurrentWeather("Isle of Berk")
        val throwable = result.exceptionOrNull()

        assertThat(result.isFailure).isEqualTo(true)
        assertThat(throwable).isNotNull()
    }

}