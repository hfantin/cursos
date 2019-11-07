package hamilton.com.br.financask.model

import java.math.BigDecimal
import java.util.*

data class Transacao(val valor: BigDecimal,
                     val categoria: String = "Indefinido",
                     val tipo: Tipo,
                     val data: Calendar = Calendar.getInstance())