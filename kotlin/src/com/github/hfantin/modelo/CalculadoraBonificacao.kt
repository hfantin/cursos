package com.github.hfantin.modelo

import com.github.hfantin.modelo.Funcionario

class CalculadoraBonificacao {

    var total: Double = 0.0
        private set

    fun registra(funcionario: Funcionario){
        this.total += funcionario.bonificacao
    }

}