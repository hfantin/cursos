package hamilton.com.br.financask.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formataParaBrasileiro() : String{
    val formatoData= "dd/MM/yyyy"
    return SimpleDateFormat(formatoData).format(this.time)
}