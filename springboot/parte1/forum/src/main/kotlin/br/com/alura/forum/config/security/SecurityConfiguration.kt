package br.com.alura.forum.config.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@EnableWebSecurity
@Configuration
class SecurityConfiguration : WebSecurityConfigurerAdapter() {


    @Autowired
    private lateinit var autenticacaoService: AutenticacaoService

    // configuracoes de autenticacao
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(autenticacaoService).passwordEncoder(BCryptPasswordEncoder())

    }


    // configuracoes de recursos estaticos(js, css, imagens, etc
    override fun configure(web: WebSecurity) {

    }

    // configuracoes de autorizacao - urls, etc
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/v1/topicos").permitAll()
                .antMatchers(HttpMethod.GET, "/v1/topicos/*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
    }
}
