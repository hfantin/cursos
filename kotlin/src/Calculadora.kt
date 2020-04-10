import model.funcionario.Funcionario

class Calculadora {

    var total = 0.0
        private set

    fun registra(f: Funcionario) {
        println("registrando ${f.nome}")
        total += f.bonificacao
    }
//
//    fun registra(f: Diretor) {
//        total += f.bonificacao
//    }
//
//    fun registra(f: Gerente) {
//        total += f.bonificacao
//    }


}