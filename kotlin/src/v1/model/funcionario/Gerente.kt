package v1.model.funcionario

class Gerente(
    nome: String,
    cpf: String,
    salario: Double,
    val senha: Int
) : Funcionario(
    nome,
    cpf,
    salario
) {

    override val bonificacao
        get() = salario * 0.1 + salario

    fun autenticacao(senha: Int) = this.senha == senha
    override fun toString() = "nome='$nome', cpf='$cpf', salario=$salario, senha=$senha"


}