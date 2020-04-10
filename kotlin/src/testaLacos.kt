fun testaLacos() {
    // for (i in 5 downTo 1) - inverso
    // for (i in 5 downTo 1 step 2) - inverso pulando os pares
    for (i in 1..5) {
        val titular = "tosco $i"
        val conta = 1000 + i
        var saldo = i + 10.0
        println("titular $titular")
        println("conta  $conta")
        println("saldo $saldo")
        testaCondicoes(saldo)
        println()
    }
}
fun testaCondicoes(saldo: Double) {
    /*
    if (saldo > 0.0) {
        println("saldo é positivo")
    } else if(saldo == 0.0) {
        println("saldo é neutro")
    } else {
        println("saldo é negativo")
    }
    */
    when {
        saldo > 0.0 -> println("saldo é positivo")
        saldo == 0.0 -> println("saldo é neutro")
        else -> println("saldo é negativo")
    }
}