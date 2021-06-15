package ankit.com.data.repository

import ankit.com.data.model.toDomainModel
import ankit.com.data.remote.WeatherApiService
import ankit.com.domain.core.ApiResponse
import ankit.com.domain.model.CurrentWeatherDomainResponse
import ankit.com.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by AnkitSingh on 6/12/21.
 */
class WeatherRepositoryImpl @Inject constructor(private val weatherApiService: WeatherApiService): WeatherRepository {

    override fun getCurrentLocationDetails(lat: Double, lon: Double): Flow<ApiResponse<CurrentWeatherDomainResponse>> {
        return flow {
            try {
                val response = weatherApiService.getCurrentWeatherByUserLocation(lat, lon)
                emit(ApiResponse.Success(response.toDomainModel()))
            } catch (exception: Exception) {
                emit(ApiResponse.Error(exception))
            }
        }
    }

    override fun getWeatherDetailsByCity(city: String): Flow<ApiResponse<CurrentWeatherDomainResponse>> {
        return flow {
            try {
                val response = weatherApiService.getCurrentWeatherByCityName(city)
                emit(ApiResponse.Success(response.toDomainModel()))
            } catch (exception: Exception) {
                emit(ApiResponse.Error(exception))
            }
        }
    }
}