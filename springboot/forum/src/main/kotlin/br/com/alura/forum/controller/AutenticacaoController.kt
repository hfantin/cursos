package br.com.alura.forum.controller

import br.com.alura.forum.config.security.AutenticacaoService
import br.com.alura.forum.config.security.TokenService
import br.com.alura.forum.dto.TokenDto
import br.com.alura.forum.form.LoginForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/auth")
class AutenticacaoController {

    @Autowired
    private lateinit var autenticationManager: AuthenticationManager

    @Autowired
    private lateinit var tokenService: TokenService

    @PostMapping
    fun autenticar(@RequestBody @Valid form: LoginForm): ResponseEntity<TokenDto> {
        return try {
            val authenticate = autenticationManager.authenticate(UsernamePasswordAuthenticationToken(form.email, form.senha))
            val token = tokenService.gerarToken(authenticate)
            ResponseEntity.ok(TokenDto(token, "Bearer"))
        } catch (e: AuthenticationException) {
            ResponseEntity.badRequest().build()
        }
    }

}

