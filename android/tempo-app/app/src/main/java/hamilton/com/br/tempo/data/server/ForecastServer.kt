package hamilton.com.br.tempo.data.server

import hamilton.com.br.tempo.data.db.ForecastDb
import hamilton.com.br.tempo.domain.datasource.ForecastDataSource
import hamilton.com.br.tempo.domain.model.ForecastList

/**
 * Created by hamilton on 21/11/17.
 */
class ForecastServer(private val dataMapper : ServerDataMapper = ServerDataMapper(), private val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
//        Log.i("ForecastServer", "requestForecastByZipCode($zipCode) - converted=$converted")
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long) = throw UnsupportedOperationException()

}