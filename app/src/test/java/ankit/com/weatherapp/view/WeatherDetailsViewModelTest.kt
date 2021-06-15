package ankit.com.weatherapp.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import ankit.com.domain.core.ApiResponse
import ankit.com.domain.usecase.WeatherByCityNameUseCase
import ankit.com.domain.usecase.WeatherByUserLocationUseCase
import ankit.com.weatherapp.mapper.toPresentation
import ankit.com.weatherapp.model.CurrentWeatherDetailsPresentationModel
import ankit.com.weatherapp.testdata.PresentationTestData.getWeatherDataByCity
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by AnkitSingh on 6/14/21.
 */
@RunWith(MockitoJUnitRunner::class)
class WeatherDetailsViewModelTest {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var weatherByCityNameUseCase: WeatherByCityNameUseCase

    @Mock
    private lateinit var weatherByUserLocationUseCase: WeatherByUserLocationUseCase

    @Mock
    private lateinit var currentWeatherDetailsObserver: Observer<CurrentWeatherDetailsPresentationModel>

    private lateinit var viewModel: WeatherDetailsViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = WeatherDetailsViewModel(weatherByCityNameUseCase, weatherByUserLocationUseCase)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    //TODO Fix the issue for spannable string mock
    @Test
    fun `get weather by city name usecase returns weather data success scenario`() = testCoroutineScope.runBlockingTest {
        val testData = getWeatherDataByCity()

        whenever(weatherByCityNameUseCase("London")).thenReturn(flowOf(ApiResponse.Success(testData)))

        viewModel.getCurrentWeatherDetailsByCityName("London")
        viewModel.currentWeatherDetails.observeForever(currentWeatherDetailsObserver)

        verify(currentWeatherDetailsObserver).onChanged(testData.toPresentation())

        viewModel.currentWeatherDetails.removeObserver(currentWeatherDetailsObserver)
    }

    @Test
    fun `get weather by user location usecase returns weather data success scenario`() = testCoroutineScope.runBlockingTest {
        val testData = getWeatherDataByCity()

        val params = WeatherByUserLocationUseCase.LocationParams(532.1, 20.5)
        whenever(weatherByUserLocationUseCase(params )).thenReturn(flowOf(ApiResponse.Success(testData)))

        viewModel.getCurrentLocationWeatherDetails(532.1, 20.5)
        viewModel.currentWeatherDetails.observeForever(currentWeatherDetailsObserver)

        verify(currentWeatherDetailsObserver).onChanged(testData.toPresentation())

        viewModel.currentWeatherDetails.removeObserver(currentWeatherDetailsObserver)
    }
}