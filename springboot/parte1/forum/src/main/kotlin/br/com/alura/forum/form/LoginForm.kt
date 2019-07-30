package br.com.alura.forum.form

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class LoginForm(
        @field:NotNull @field:NotEmpty
        var email: String,
        @field:NotNull @field:NotEmpty
        var senha: String
)