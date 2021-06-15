package ankit.com.data.model

import ankit.com.domain.model.CurrentWeatherDomainResponse

data class CurrentWeatherDetails(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val weather: List<Weather>,
    val wind: Wind
)

fun CurrentWeatherDetails.toDomainModel(): CurrentWeatherDomainResponse {
    return CurrentWeatherDomainResponse(
        base = this.base,
        clouds = this.clouds.toDomainModel(),
        cod = this.cod,
        coord = this.coord.toDomainModel(),
        dt = this.dt,
        id = this.id,
        main = this.main.toDomainModel(),
        name = this.name,
        sys = this.sys.toDomainModel(),
        timezone = this.timezone,
        weather = this.weather.map { it.toDomainModel() },
        wind = this.wind.toDomainModel())
}