package hamilton.com.br.tempo

/**
 * Created by hamilton on 13/11/17.
 */
class Colecoes {

    val list = listOf(1, 2, 3, 4, 5, 6)

    fun listAnyDivisao2() = list.any { it % 2 == 0 }
    fun listAnyMaior10() = list.any { it > 10 }

    fun listAllDivisao2() = list.all { it % 2 == 0 }
    fun listAllMenor10() = list.all { it < 10 }

    fun listCount() = list.count { it % 2 == 0 }

    fun listFold() = list.fold(0) { total, next -> total + next }
    fun listFoldRight() = list.foldRight(0) { total, next -> total + next }
    fun listForEach() = list.forEach() { println(it)}
    fun listForEachIndexed() = list.forEachIndexed { index, value -> println("posicao $index contem o valor $value")}


    fun listMax() = list.max()
    fun listMaxBy() = list.maxBy { -it }
    fun listMin() = list.min()
    fun listMinBy() = list.minBy { -it }

    fun listNone() = list.none { it % 7 == 0 }

    fun listReduce() = list.reduce{ total, next -> total + next }
    fun listReduceRight() = list.reduceRight{ total, next -> total + next }

    fun listSumBy() = list.sumBy { it % 2 }
}