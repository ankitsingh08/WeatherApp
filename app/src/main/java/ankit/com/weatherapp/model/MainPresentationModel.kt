package ankit.com.weatherapp.model

import android.text.SpannableString

data class MainPresentationModel(
    val feels_like: SpannableString,
    val humidity: SpannableString,
    val pressure: SpannableString,
    val temp: String,
    val temp_max: SpannableString,
    val temp_min: SpannableString
)
