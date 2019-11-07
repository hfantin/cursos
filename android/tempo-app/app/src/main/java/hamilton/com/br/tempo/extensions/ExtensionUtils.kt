package hamilton.com.br.tempo.extensions

import java.text.DateFormat
import java.util.*

/**
 * Created by hamilton on 22/11/17.
 */
fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}