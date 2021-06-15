package ankit.com.weatherapp.model

data class CurrentWeatherDetailsPresentationModel(
    val base: String,
    val clouds: CloudsPresentationModel,
    val cod: Int,
    val coord: CoordPresentationModel,
    val dt: Int,
    val id: Int,
    val main: MainPresentationModel,
    val name: String,
    val sys: SysPresentationModel,
    val timezone: Int,
    val weather: List<WeatherPresentationModel>,
    val wind: WindPresentationModel
)