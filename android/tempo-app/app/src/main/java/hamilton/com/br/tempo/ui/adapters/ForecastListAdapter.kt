package hamilton.com.br.tempo.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import hamilton.com.br.tempo.R
import hamilton.com.br.tempo.domain.model.Forecast
import hamilton.com.br.tempo.domain.model.ForecastList
import hamilton.com.br.tempo.extensions.ctx
import hamilton.com.br.tempo.extensions.toDateString
import kotlinx.android.synthetic.main.item_forecast.view.*
import java.text.DateFormat
import java.util.*


/**
 * Created by hamilton on 18/10/17.
 */

class ForecastListAdapter(private val weekForecast: ForecastList, private val itemClick: (Forecast) -> Unit) :
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount() = weekForecast.size

    class ViewHolder(view: View, private val itemClick: (Forecast) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text = date.toDateString()
                itemView.description.text = description
                itemView.maxTemperature.text = "${high}º"
                itemView.minTemperature.text = "${low}º"
                itemView.setOnClickListener { itemClick(this) }
            }
        }

    }

}
