package ankit.com.domain.usecase

import ankit.com.domain.core.ApiResponse
import ankit.com.domain.di.IoDispatcher
import ankit.com.domain.model.CurrentWeatherDomainResponse
import ankit.com.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by AnkitSingh on 6/12/21.
 */
class WeatherByCityNameUseCase @Inject constructor(private val repository: WeatherRepository,
                                                   @IoDispatcher ioDispatcher: CoroutineDispatcher
): BaseUseCase<String, CurrentWeatherDomainResponse>(ioDispatcher) {

    override fun execute(parameters: String): Flow<ApiResponse<CurrentWeatherDomainResponse>> {
        return repository.getWeatherDetailsByCity(parameters)
    }

}