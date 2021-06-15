package ankit.com.data.model

import ankit.com.domain.model.WeatherDomainModel

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

fun Weather.toDomainModel(): WeatherDomainModel {
    return WeatherDomainModel(
        description = this.description,
        icon = this.icon,
        id = this.id,
        main = this.main
    )
}