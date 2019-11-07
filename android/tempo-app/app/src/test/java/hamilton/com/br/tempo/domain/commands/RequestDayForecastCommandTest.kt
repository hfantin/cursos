package hamilton.com.br.tempo.domain.commands

import hamilton.com.br.tempo.domain.datasource.ForecastProvider
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

/**
 * Created by hamilton on 20/12/17.
 */
class RequestDayForecastCommandTest {

    @Test
    fun testProviderIsCalled() {
        val forecastProvider = mock(ForecastProvider::class.java)
        val command = RequestDayForecastCommand(2, forecastProvider)
        command.execute()
        verify(forecastProvider).requestForecast(2)
    }
}