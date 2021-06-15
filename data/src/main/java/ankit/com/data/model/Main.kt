package ankit.com.data.model

import ankit.com.domain.model.MainDomainModel

data class Main(
    val feels_like: Double,
    val humidity: Double,
    val pressure: Double,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)

fun Main.toDomainModel(): MainDomainModel {
    return MainDomainModel(
        feels_like = this.feels_like,
        humidity = this.humidity,
        pressure = this.pressure,
        temp = this.temp,
        temp_max = this.temp_max,
        temp_min = this.temp_min
    )
}