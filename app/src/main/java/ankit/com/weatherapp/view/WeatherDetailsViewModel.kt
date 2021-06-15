package ankit.com.weatherapp.view

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ankit.com.domain.core.successOr
import ankit.com.domain.usecase.WeatherByCityNameUseCase
import ankit.com.domain.usecase.WeatherByUserLocationUseCase
import ankit.com.weatherapp.mapper.toPresentation
import ankit.com.weatherapp.model.CurrentWeatherDetailsPresentationModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Created by AnkitSingh on 6/12/21.
 */
class WeatherDetailsViewModel @ViewModelInject constructor(
    private val currentWeatherUseCase: WeatherByCityNameUseCase,
    private val locationUseCase: WeatherByUserLocationUseCase
) : ViewModel() {

    private val _currentWeatherDetails = MutableLiveData<CurrentWeatherDetailsPresentationModel>()
    val currentWeatherDetails: LiveData<CurrentWeatherDetailsPresentationModel> =
        _currentWeatherDetails

    fun getCurrentLocationWeatherDetails(lat: Double, long: Double) {
        viewModelScope.launch {
            locationUseCase(WeatherByUserLocationUseCase.LocationParams(lat, long))
                .map { it.successOr(null) }
                .collect {
                    _currentWeatherDetails.value = it?.toPresentation()
                }
        }
    }

    fun getCurrentWeatherDetailsByCityName(cityName: String) {
        viewModelScope.launch {
            currentWeatherUseCase(cityName)
                .map { it.successOr(null) }
                .collect {
                    _currentWeatherDetails.value = it?.toPresentation()
                }
        }
    }


}