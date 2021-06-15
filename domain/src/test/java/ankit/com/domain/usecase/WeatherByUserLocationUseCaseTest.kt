package ankit.com.domain.usecase

import ankit.com.domain.core.ApiResponse
import ankit.com.domain.core.successOr
import ankit.com.domain.repository.WeatherRepository
import ankit.com.domain.usecase.testdata.DomainTestData
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.IOException

/**
 * Created by AnkitSingh on 6/14/21.
 */
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class WeatherByUserLocationUseCaseTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @Mock
    private lateinit var weatherRepository: WeatherRepository

    private lateinit var weatherByUserLocationUseCase: WeatherByUserLocationUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        weatherByUserLocationUseCase = WeatherByUserLocationUseCase(weatherRepository, testCoroutineDispatcher)
        Dispatchers.setMain(testCoroutineDispatcher)
    }


    @Test
    fun `weather details for user location returned by repository success scenario`() = testCoroutineScope.runBlockingTest {
        val expectedResult =  flowOf(ApiResponse.Success(DomainTestData.getWeatherDetails()))
        whenever(weatherRepository.getCurrentLocationDetails(52.23, 63.37)).thenReturn(expectedResult)

        val params = WeatherByUserLocationUseCase.LocationParams(52.23, 63.37)
        val result = weatherByUserLocationUseCase.invoke(params)

        result.collect {data ->
            assertEquals(DomainTestData.getWeatherDetails(), data.successOr(null))
        }
    }

    @Test
    fun  `repository returns error when exception is thrown while fetching weather details for user location`()  = testCoroutineScope.runBlockingTest{
        val expectedError =  ApiResponse.Error(IOException())
        whenever(weatherRepository.getCurrentLocationDetails(83.33, 64.33)).thenReturn(
            flowOf(expectedError)
        )

        val params = WeatherByUserLocationUseCase.LocationParams(83.33, 64.33)
        val result = weatherByUserLocationUseCase.invoke(params)

        result.collect{ error ->
            assertEquals(expectedError, error)
        }
    }
}