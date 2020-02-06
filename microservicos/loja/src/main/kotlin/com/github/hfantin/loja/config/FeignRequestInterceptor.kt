package com.github.hfantin.loja.config

import feign.RequestInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails


@Configuration
class FeignRequestInterceptor {

    @Bean
    fun getInterceptorDeAutenticacao() = RequestInterceptor { template ->
        val authentication = SecurityContextHolder.getContext().authentication ?: return@RequestInterceptor
        val details = authentication.details as OAuth2AuthenticationDetails
        template.header("Authorization", "Bearer${details.tokenValue}")
    }

}