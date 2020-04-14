package v1.model.funcionario

class Diretor(
    nome: String,
    cpf: String,
    salario: Double,
    val senha: Int,
    val plr: Double
) : Funcionario(nome, cpf, salario) {

    override val bonificacao
        get() = salario * 0.1 + salario + plr

    fun autenticacao(senha: Int) = this.senha == senha

    override fun toString() = "nome='$nome', cpf='$cpf', salario=$salario, senha=$senha, plr=$plr"

}