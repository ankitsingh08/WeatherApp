package ankit.com.domain.model

data class CurrentWeatherDomainResponse(
    val base: String,
    val clouds: CloudsDomainModel,
    val cod: Int,
    val coord: CoordDomainModel,
    val dt: Int,
    val id: Int,
    val main: MainDomainModel,
    val name: String,
    val sys: SysDomainModel,
    val timezone: Int,
    val weather: List<WeatherDomainModel>,
    val wind: WindDomainModel
)