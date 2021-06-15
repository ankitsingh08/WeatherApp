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
        //please add api key to run the app
        const val API_KEY = ""
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