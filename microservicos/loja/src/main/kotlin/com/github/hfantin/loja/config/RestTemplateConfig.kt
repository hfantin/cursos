package com.github.hfantin.loja.config

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate


@Configuration
class RestTemplateConfig {

    /**
     * this is the restTemplate used by OrganizationRestTemplateClient and must use @LoadBalance annotation
     * to tells springcloud to create a ribbon backed RestTemplate class
     */
    @Bean
    @LoadBalanced
    fun restTemplate() = RestTemplate()


}
