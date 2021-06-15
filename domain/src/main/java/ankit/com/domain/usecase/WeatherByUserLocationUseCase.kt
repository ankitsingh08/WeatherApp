package ankit.com.domain.usecase

import ankit.com.domain.core.ApiResponse
import ankit.com.domain.di.IoDispatcher
import ankit.com.domain.model.CurrentWeatherDomainResponse
import ankit.com.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by AnkitSingh on 6/13/21.
 */
class WeatherByUserLocationUseCase @Inject constructor(private val repository: WeatherRepository,
                                                   @IoDispatcher ioDispatcher: CoroutineDispatcher
): BaseUseCase<WeatherByUserLocationUseCase.LocationParams, CurrentWeatherDomainResponse>(ioDispatcher) {

    override fun execute(parameters: LocationParams): Flow<ApiResponse<CurrentWeatherDomainResponse>> {
        return repository.getCurrentLocationDetails(parameters.lat, parameters.lon)
    }

    class LocationParams(
        val lat: Double,
        val lon: Double
    )
}