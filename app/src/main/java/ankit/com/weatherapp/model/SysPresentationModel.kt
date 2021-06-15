package ankit.com.weatherapp.model

data class SysPresentationModel(
    val country: String,
    val id: Int,
    val message: Double,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)