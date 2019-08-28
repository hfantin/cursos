package ex10

fun main() {
    val mapa = mapOf(
        "primeiro" to 1,
        "segundo" to 2,
        "terceiro" to 3
    )
    println("elementos $mapa")
    println("keys ${mapa.keys}")
    println("values ${mapa.values}")
    println("empty ${mapa.isEmpty()}")
    println("size ${mapa.size}")
    println("contains ${mapa.containsKey("primeiro")}")
    println("key[] ${mapa["primeiro"]}")

    // mutable
    println("******* mutable map ***********")
    val mapa2 = mutableMapOf(
        "primeiro" to 1,
        "segundo" to 2,
        "terceiro" to 3
    )

    mapa2["sexto"] = 6
    mapa2 += "setimo" to 7
    mapa2["terceiro"] = 8
    mapa2.remove("segundo")
    println("elementos $mapa2")


}