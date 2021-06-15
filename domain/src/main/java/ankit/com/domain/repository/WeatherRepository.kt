package ankit.com.domain.repository

import ankit.com.domain.core.ApiResponse
import ankit.com.domain.model.CurrentWeatherDomainResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by AnkitSingh on 6/12/21.
 */
interface WeatherRepository {

    fun getCurrentLocationDetails(lat: Double, lon: Double): Flow<ApiResponse<CurrentWeatherDomainResponse>>

    fun getWeatherDetailsByCity(city: String): Flow<ApiResponse<CurrentWeatherDomainResponse>>

}