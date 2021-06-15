package ankit.com.weatherapp.view

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import ankit.com.weatherapp.databinding.WeatherDetailsFragmentBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by AnkitSingh on 6/12/21.
 */

@AndroidEntryPoint
class WeatherDetailsFragment : Fragment() {

    private val weatherDetailsViewModel: WeatherDetailsViewModel by viewModels()

    private lateinit var binding: WeatherDetailsFragmentBinding

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val REQUEST_PERMISSIONS_REQUEST_CODE = 16363

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeatherDetailsFragmentBinding.inflate(inflater, container, false)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity as FragmentActivity)
        initializeUI()
        return binding.root
    }

    private fun initializeUI() {
        binding.searchBtn.setOnClickListener {
            weatherDetailsViewModel.getCurrentWeatherDetailsByCityName(binding.search.text.toString())
            binding.search.onEditorAction(EditorInfo.IME_ACTION_DONE)
        }

        binding.gpsSearchBtn.setOnClickListener {
            if (!checkPermissions()) {
                startLocationPermissionRequest()
            } else {
                getLastLocation()
            }
        }

        weatherDetailsViewModel.currentWeatherDetails.observe(
            viewLifecycleOwner,
            Observer { response ->
                response?.let {
                    binding.cityName.text = response.name
                    binding.currentTemp.text = response.main.temp
                    binding.airPressure.text = response.main.pressure
                    binding.humidity.text = response.main.humidity
                    binding.feelsLike.text = response.main.feels_like
                    binding.windSpeed.text = response.wind.speed
                    binding.minTemperature.text = response.main.temp_min
                    binding.maxTemperature.text = response.main.temp_max
                    binding.rootCardView.visibility = View.VISIBLE
                }
            }
        )
    }

    private fun checkPermissions() =
        activity?.baseContext?.let { ActivityCompat.checkSelfPermission(it, ACCESS_COARSE_LOCATION) } == PERMISSION_GRANTED

    private fun startLocationPermissionRequest() {
        requestPermissions(arrayOf(ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION), REQUEST_PERMISSIONS_REQUEST_CODE)
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationClient.lastLocation
            .addOnCompleteListener { taskLocation ->
                if (taskLocation.isSuccessful && taskLocation.result != null) {
                    val location = taskLocation.result
                    location?.latitude?.let {
                        weatherDetailsViewModel.getCurrentLocationWeatherDetails(
                            it, location.longitude
                        )
                    }
                }
            }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            when {
                // Permission granted.
                (grantResults[0] == PackageManager.PERMISSION_GRANTED) -> getLastLocation()
                else -> {
                    Log.i(WeatherDetailsFragment::class.simpleName, "cancel interaction")
                }
            }
        }
    }

}