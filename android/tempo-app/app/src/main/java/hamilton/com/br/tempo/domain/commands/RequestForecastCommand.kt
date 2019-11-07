package hamilton.com.br.tempo.domain.commands

import hamilton.com.br.tempo.domain.datasource.ForecastProvider
import hamilton.com.br.tempo.domain.model.ForecastList

/**
 * Created by hamilton on 23/10/17.
 */
class RequestForecastCommand(private val zipCode: Long,
                             private val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute() = forecastProvider.requestByZipCode(zipCode, DAYS)
}