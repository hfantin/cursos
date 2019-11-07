package hamilton.com.br.tempo.extensions

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 * Created by hamilton on 22/11/17.
 */

fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)