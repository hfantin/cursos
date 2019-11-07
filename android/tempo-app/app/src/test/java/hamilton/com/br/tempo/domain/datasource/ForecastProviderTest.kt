package hamilton.com.br.tempo.domain.datasource

import hamilton.com.br.tempo.domain.model.Forecast
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when` as whenever

/**
 * Created by hamilton on 20/12/17.
 */
class ForecastProviderTest {

    @Test
    fun testDataSourceReturnsValue() {
        val ds = mock(ForecastDataSource::class.java)
        whenever(ds.requestDayForecast(0)).then {
            Forecast(0, 0, "desc", 20, 0, "url")
        }

        val provider = ForecastProvider(listOf(ds))
        assertNotNull(provider.requestForecast(0))

    }


}

