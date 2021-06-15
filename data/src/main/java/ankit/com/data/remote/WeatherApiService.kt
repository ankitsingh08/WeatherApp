package ankit.com.data.remote

import ankit.com.data.model.CurrentWeatherDetails
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by AnkitSingh on 6/12/21.
 */
interface WeatherApiService {

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        const val API_KEY = "a56317f0d67753ad926f8d0e196db95a"
    }

    @GET("weather")
    suspend fun getCurrentWeatherByUserLocation(
        @Query("lat") lat: Double, @Query("lon")
        lon: Double
    ): CurrentWeatherDetails

    @GET("weather")
    suspend fun getCurrentWeatherByCityName(
        @Query("q") city: String
    ): CurrentWeatherDetails


}