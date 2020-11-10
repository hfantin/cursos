package com.github.hfantin.entidades

import io.quarkus.elytron.security.common.BcryptUtil
import io.quarkus.security.jpa.Password
import io.quarkus.security.jpa.Roles
import io.quarkus.security.jpa.UserDefinition
import io.quarkus.security.jpa.Username
import javax.json.bind.annotation.JsonbTransient
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
@UserDefinition
data class Usuario(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0L,
        @field:NotBlank(message="Nome invalido")
        var nome: String = "",
        @field:NotBlank(message="CPF invalido")
        var cpf: String = "",
        @field:NotBlank(message="Usuario invalido")
        @Username
        var username: String = "",
        @field:NotBlank(message="Senha invalida")
        @Password
        @JsonbTransient
        var password: String = "",
        @Roles
        var role: String = ""
)


fun Usuario.adicionar(){
        this.password = BcryptUtil.bcryptHash(this.password)
        this.role = if (this.username == "alura") "admin" else "user"
}