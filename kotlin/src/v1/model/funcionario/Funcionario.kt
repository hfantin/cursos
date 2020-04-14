package v1.model.funcionario

abstract class Funcionario(
    var nome: String,
    val cpf: String,
    val salario: Double
) {
    abstract val bonificacao: Double

}