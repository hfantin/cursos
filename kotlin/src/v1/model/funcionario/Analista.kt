package v1.model.funcionario

class Analista(
    nome: String,
    cpf: String,
    salario: Double
) : Funcionario(
    nome,
    cpf,
    salario
) {
    override val bonificacao
        get() = salario * 0.1

    override fun toString() = "nome='$nome', cpf='$cpf', salario=$salario"
}