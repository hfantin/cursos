package hamilton.com.br.tempo.domain.commands

import hamilton.com.br.tempo.domain.datasource.ForecastProvider
import hamilton.com.br.tempo.domain.model.Forecast

/**
 * Created by hamilton on 22/11/17.
 */
class RequestDayForecastCommand (val id: Long,
                                 private val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<Forecast>{

    override fun execute() = forecastProvider.requestForecast(id)

}
