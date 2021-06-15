package ankit.com.data.model

import ankit.com.domain.model.CloudsDomainModel

data class Clouds(
    val all: Int
)

fun Clouds.toDomainModel(): CloudsDomainModel {
    return CloudsDomainModel(
        all = this.all
    )
}