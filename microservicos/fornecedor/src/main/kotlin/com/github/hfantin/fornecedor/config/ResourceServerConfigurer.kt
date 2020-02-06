package com.github.hfantin.fornecedor.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter

@Configuration
class ResourceServerConfigurer: ResourceServerConfigurerAdapter(){

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/pedido")
                .hasRole("USER")

//                .anyRequest()
//                .authenticated()
    }
}