package hamilton.com.br.tempo.data.server

import hamilton.com.br.tempo.domain.model.ForecastList
import hamilton.com.br.tempo.domain.model.Forecast as ModelForecast
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by hamilton on 23/10/17.
 */
class ServerDataMapper {

    fun convertToDomain(zipCode: Long, forecast: ForecastResult) =
            with(forecast){
                ForecastList(zipCode, city.name, city.country, convertForecastListToDomain(forecast.list))
            }


    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast) = with(forecast){
        ModelForecast(-1, dt, weather[0].description, temp.max.toInt(), temp.min.toInt(),
                generateIconurl(weather[0].icon))
    }

    private fun generateIconurl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"

}