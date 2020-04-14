package v1.main

import v1.model.funcionario.Funcionario

class Calculadora {

    var total = 0.0
        private set

    fun registra(f: Funcionario) {
        println("registrando ${f.nome}")
        total += f.bonificacao
    }
//
//    fun registra(f: com.github.hfantin.modelo.Diretor) {
//        total += f.bonificacao
//    }
//
//    fun registra(f: com.github.hfantin.modelo.Gerente) {
//        total += f.bonificacao
//    }


}