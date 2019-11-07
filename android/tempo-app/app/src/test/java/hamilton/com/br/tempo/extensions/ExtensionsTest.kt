package hamilton.com.br.tempo.extensions

import hamilton.com.br.tempo.extensions.toDateString
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.DateFormat

/**
 * Created by hamilton on 20/12/17.
 */
class ExtensionsTest {

    @Test fun testLongToDateString() {
        assertEquals("19/10/2015", 1445275635000L.toDateString())
    }

    @Test fun testDateStringFullFormat() {
        assertEquals("Segunda-feira, 19 de Outubro de 2015",
                1445275635000L.toDateString(DateFormat.FULL))
    }


}