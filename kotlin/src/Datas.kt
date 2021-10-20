import java.util.*

class Datas {

    fun teste() {
        val d1 = Date(2021, 8, 1)
        val d2 = Date(2021, 8, 2)
        val horas = 1000 * 60 * 60
        var time: Long = Date().time
        println((d2.time - d1.time) / horas)
//        println(d2)

    }

}

fun Date.isMaior(date: Date, period: Long = 1000 * 60 * 60L) = Date().time - date.time / period

fun main() {
    println("datas")
    Datas().teste()

}