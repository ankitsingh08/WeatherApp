package ankit.com.domain.usecase

import ankit.com.domain.core.ApiResponse
import ankit.com.domain.core.successOr
import ankit.com.domain.repository.WeatherRepository
import ankit.com.domain.usecase.testdata.DomainTestData.getWeatherDetails
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
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
class WeatherByCityNameUseCaseTest {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @Mock
    private lateinit var weatherRepository: WeatherRepository

    private lateinit var weatherByCityNameUseCase: WeatherByCityNameUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        weatherByCityNameUseCase = WeatherByCityNameUseCase(weatherRepository, testCoroutineDispatcher)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun `weather details for city is returned by repository success scenario`() = testCoroutineScope.runBlockingTest {
        val expectedResult =  flowOf(ApiResponse.Success(getWeatherDetails()))
        whenever(weatherRepository.getWeatherDetailsByCity("London")).thenReturn(expectedResult)

        val result = weatherByCityNameUseCase.invoke("London")

        result.collect {data ->
            assertEquals(getWeatherDetails(), data.successOr(null))
        }
    }

    @Test
    fun  `repository returns error when exception is thrown while fetching weather details for city`()  = testCoroutineScope.runBlockingTest{
        val expectedError =  ApiResponse.Error(IOException())
        whenever(weatherRepository.getWeatherDetailsByCity("London")).thenReturn(
            flowOf(expectedError)
        )

        val result = weatherByCityNameUseCase.invoke("London")

        result.collect{ error ->
            assertEquals(expectedError, error)
        }
    }
}