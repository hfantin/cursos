package com.github.hfantin.cloudstreamproducer.sources

import org.springframework.cloud.stream.annotation.EnableBinding

@EnableBinding(RestSource::class)
class RestSourceImpl