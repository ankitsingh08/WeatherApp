package ankit.com.data.repository

import ankit.com.data.model.toDomainModel
import ankit.com.data.remote.WeatherApiService
import ankit.com.domain.core.ApiResponse
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import testdata.TestData

/**
 * Created by AnkitSingh on 6/14/21.
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherRepositoryImplTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @Mock
    private lateinit var weatherApiService: WeatherApiService

    private lateinit var weatherRepositoryImpl: WeatherRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        weatherRepositoryImpl = WeatherRepositoryImpl(weatherApiService)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun `get weather data by city scenario`()  = testCoroutineScope.runBlockingTest {
        val testData = TestData.getWeatherData()
        val expectedOutput = ApiResponse.Success(testData.toDomainModel())
        whenever(weatherApiService.getCurrentWeatherByCityName("London")).thenReturn(testData)

        val flow = weatherRepositoryImpl.getWeatherDetailsByCity("London")

        // Verify
        flow.collect { data->
            assertEquals(expectedOutput, data)
        }
    }

    @Test
    fun `get weather data by city Error scenario`()  = testCoroutineScope.runBlockingTest {
        val exception = NullPointerException()
        val expectedOutput = ApiResponse.Error(exception)
        whenever(weatherApiService.getCurrentWeatherByCityName("London")).thenThrow(exception)

        val flow = weatherRepositoryImpl.getWeatherDetailsByCity("London")

        // Verify
        flow.collect{ data ->
            assertEquals(expectedOutput, data)
        }
    }

    @Test
    fun `get weather data by user location success scenario`()  = testCoroutineScope.runBlockingTest {
        val testData = TestData.getWeatherData()
        val expectedOutput = ApiResponse.Success(testData.toDomainModel())
        whenever(weatherApiService.getCurrentWeatherByUserLocation(100.12, 20.1)).thenReturn(testData)

        val flow = weatherRepositoryImpl.getCurrentLocationDetails(100.12, 20.1)

        // Verify
        flow.collect { data->
            assertEquals(expectedOutput, data)
        }
    }

    @Test
    fun `get weather data by user location Error scenario`()  = testCoroutineScope.runBlockingTest {
        val exception = NullPointerException()
        val expectedOutput = ApiResponse.Error(exception)
        whenever(weatherApiService.getCurrentWeatherByUserLocation(200.86, 55.92)).thenThrow(exception)

        val flow = weatherRepositoryImpl.getCurrentLocationDetails(200.86, 55.92)

        // Verify
        flow.collect{ data ->
            assertEquals(expectedOutput, data)
        }
    }
}