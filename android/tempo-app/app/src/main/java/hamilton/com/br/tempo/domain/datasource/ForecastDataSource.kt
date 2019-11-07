package hamilton.com.br.tempo.domain.datasource

import hamilton.com.br.tempo.domain.model.Forecast
import hamilton.com.br.tempo.domain.model.ForecastList

/**
 * Created by hamilton on 21/11/17.
 */
interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
    fun requestDayForecast(id: Long): Forecast?
}