package com.github.hfantin.clima.kotlin.extensions

import java.text.DateFormat
import java.text.DecimalFormat
import java.util.*

fun Date.formatar(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale("pt", "BR"))
    return df.format(this)
}

fun Double.formatar(pattern: String = "#,0") : String{
    val decimalFormat = DecimalFormat(pattern)
    return decimalFormat.format(this)
}
