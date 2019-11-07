package hamilton.com.br.tempo.domain.datasource

import hamilton.com.br.tempo.data.db.ForecastDb
import hamilton.com.br.tempo.data.server.ForecastServer
import hamilton.com.br.tempo.domain.model.Forecast
import hamilton.com.br.tempo.domain.model.ForecastList
import hamilton.com.br.tempo.extensions.firstResult


class ForecastProvider(private val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {

    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES by lazy {listOf(ForecastDb(), ForecastServer())}
//        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size >= days) res else null
    }

    fun requestForecast(id: Long): Forecast = requestToSources { it.requestDayForecast(id) }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T = sources.firstResult { f(it) }

}