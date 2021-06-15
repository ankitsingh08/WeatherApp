package ankit.com.data.model

import ankit.com.domain.model.SysDomainModel

data class Sys(
    val country: String,
    val id: Int,
    val message: Double,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)

fun Sys.toDomainModel(): SysDomainModel {
    return SysDomainModel(
        country = this.country,
        id = this.id,
        message = this.message,
        sunrise = this.sunrise,
        sunset = this.sunset,
        type = this.type
    )
}