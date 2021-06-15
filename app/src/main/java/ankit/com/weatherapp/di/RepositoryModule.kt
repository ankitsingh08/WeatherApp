package ankit.com.weatherapp.di

import ankit.com.data.repository.WeatherRepositoryImpl
import ankit.com.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Created by AnkitSingh on 6/12/21.
 */
@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(weatherRepository: WeatherRepositoryImpl): WeatherRepository
}