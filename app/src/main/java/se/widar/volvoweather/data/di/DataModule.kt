package se.widar.volvoweather.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import se.widar.volvoweather.BuildConfig
import se.widar.volvoweather.data.datasource.CurrentWeatherDataSource
import se.widar.volvoweather.data.datasource.CurrentWeatherDataSourceImpl
import se.widar.volvoweather.data.datasource.client.CurrentWeatherService
import se.widar.volvoweather.data.repository.CitiesWeatherRepository
import se.widar.volvoweather.data.repository.CitiesWeatherRepositoryImpl
import javax.inject.Qualifier

const val API_BASE_URL = "https://api.openweathermap.org/data/2.5/"

@Module(includes = [Bindings::class])
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(API_BASE_URL)
        .build()

    @Provides
    fun provideCurrentWeatherService(retrofit: Retrofit) =
        retrofit.create(CurrentWeatherService::class.java)

    @ApiKey
    @Provides
    fun provideApiKey(): String = BuildConfig.API_KEY

    @Io
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

}

@Module
@InstallIn(SingletonComponent::class)
interface Bindings {
    @Binds
    fun bindCurrentWeatherRepository(impl: CitiesWeatherRepositoryImpl): CitiesWeatherRepository

    @Binds
    fun bindCurrentWeatherDataSource(impl: CurrentWeatherDataSourceImpl): CurrentWeatherDataSource
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Io