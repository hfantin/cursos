package com.github.hfantin.cloudstreamproducer.producers

import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Source
import org.springframework.integration.annotation.InboundChannelAdapter

//@EnableBinding(Source::class)
class Producer {

//    @InboundChannelAdapter(Source.OUTPUT)
    fun sendMessage() = "Hello!!!"

}