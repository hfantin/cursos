package com.github.hfantin

import com.github.hfantin.modelo.Endereco

fun main() {

    val endereco1  = Endereco("Rua vergueiro" ,1000, cep="00000-000")
    val endereco2 = Endereco("Rua vergueiro" ,1000, cep="00000-000")
    println(endereco1 == endereco2)
    println(endereco1.hashCode())
    println(endereco2.hashCode())
}

