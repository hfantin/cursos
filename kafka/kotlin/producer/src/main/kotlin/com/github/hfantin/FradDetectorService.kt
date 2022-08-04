package com.github.hfantin

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration
import java.util.*


class FradDetectorService {
    fun properties() = Properties().apply {
        setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:29092")
        setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        setProperty(ConsumerConfig.GROUP_ID_CONFIG, FradDetectorService::class.java.simpleName)
    }
}

fun main(args: Array<String>) {
    println("fraud detector consumer")
    val consumer: KafkaConsumer<String, String> = KafkaConsumer<String, String>(FradDetectorService().properties()).apply {
        subscribe(listOf("ECOMMERCE_NEW_ORDER"))
    }
    while (true) {
        val records = consumer.poll(Duration.ofMillis(100))
        if (!records.isEmpty) {
            println("Encontrei " + records.count() + " registros")
            for (record in records) {
                println("------------------------------------------")
                println("Processando nova ordem e verificando fraudes")
                with(record) {
                    println(key())
                    println(value())
                    println(partition())
                    println(offset())
                }

                try {
                    Thread.sleep(5000)
                } catch (e: InterruptedException) {
                    // ignoring
                    e.printStackTrace()
                }
                println("Ordem processada")
            }
        }
    }
}


