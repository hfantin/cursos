package hamilton.com.br.tempo.ui.activities

import android.os.Bundle
import android.provider.Contacts
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.squareup.picasso.Picasso
import hamilton.com.br.tempo.R
import hamilton.com.br.tempo.domain.commands.RequestDayForecastCommand
import hamilton.com.br.tempo.domain.model.Forecast
import hamilton.com.br.tempo.extensions.color
import hamilton.com.br.tempo.extensions.textColor
import hamilton.com.br.tempo.extensions.toDateString
import org.jetbrains.anko.coroutines.experimental.asReference
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import java.text.DateFormat

class DetailActivity : AppCompatActivity(), ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    companion object {
        val ID = "DetailActivity:id"
        val CITY_NAME = "DetailActivity:cityName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initToolbar()

        toolbarTitle = intent.getStringExtra(CITY_NAME)
        enableHomeAsUp { onBackPressed() }

        // com  async task:
        /*
        doAsync {
            val result = RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute()
            uiThread { bindForecast(result) }
        }
        */
        // com coroutines
        val ref = asReference() // cria uma referencia fraca - weak reference(ESTUDAR!!)
        val id = intent.getLongExtra(ID, -1)
        doAsync(Contacts.Intents.UI, {
            val result = bg { RequestDayForecastCommand(id).execute() }
            // acinona o metodo da activity via referencia fraca, evitando vazamento de memoria caso o async nunca termine, por exemplo.
            ref().bindForecast(result.await())
        })
    }

    private fun bindForecast(forecast: Forecast) = with(forecast) {
        Picasso.with(ctx).load(iconUrl).into(icon)
        toolbar.subtitle = date.toDateString(DateFormat.FULL)
        weatherDescription.text = description
        bindWeather(high to maxTemperature, low to minTemperature)
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first}º"
        it.second.textColor = color(when (it.first) {
            in -50..0 -> android.R.color.holo_red_dark
            in 0..15 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_green_dark
        })
    }

}
