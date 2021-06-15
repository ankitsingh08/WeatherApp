package ankit.com.data.model

import ankit.com.domain.model.WindDomainModel

data class Wind(
    val deg: Double,
    val speed: Double
)

fun Wind.toDomainModel(): WindDomainModel {
    return WindDomainModel(
        deg = this.deg,
        speed = this.speed
    )
}