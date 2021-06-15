package ankit.com.domain.usecase.testdata

import ankit.com.domain.model.*

/**
 * Created by AnkitSingh on 6/14/21.
 */
object DomainTestData {

    internal fun getWeatherDetails(): CurrentWeatherDomainResponse {
        val weather = WeatherDomainModel("clear sky","01d", 800, "Clear")
        val weatherList = listOf(weather)

        return CurrentWeatherDomainResponse(
            "stations",
            CloudsDomainModel(90),
            200,
            CoordDomainModel(-0.13, 51.51),
            1485789600,
            2643743,
            MainDomainModel(280.32, 1012.00, 81.00, 250.15, 281.15, 271.15),
            "London",
            SysDomainModel("GB", 5091, 0.0103, 1485762037, 1485794875, 1),
            -25200,
            weatherList,
            WindDomainModel(350.00, 1.5)
        )
    }
}