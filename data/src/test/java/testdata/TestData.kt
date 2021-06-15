package testdata

import ankit.com.data.model.*
import ankit.com.data.remote.WeatherApiService
import ankit.com.domain.model.*

/**
 * Created by AnkitSingh on 6/14/21.
 */
object TestData {

    fun getWeatherData(): CurrentWeatherDetails {
        val weather = Weather("clear sky","01d", 800, "Clear")
        val weatherList = listOf(weather)

        return CurrentWeatherDetails(
            "stations",
            Clouds(90),
            200,
            Coord(-0.13, 51.51),
            1485789600,
            2643743,
            Main(280.32, 1012.00, 81.00, 250.15, 281.15, 271.15),
            "London",
            Sys("GB", 5091, 0.0103, 1485762037, 1485794875, 1),
            -25200,
            weatherList,
            Wind(350.00, 1.5)
        )
    }
}