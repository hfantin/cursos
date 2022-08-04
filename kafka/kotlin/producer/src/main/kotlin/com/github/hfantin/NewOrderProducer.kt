import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*


fun main(args: Array<String>) {
    println("New order producer")
    val producer = KafkaProducer<String, String>(properties())
    val value = "132123,67523,8888"
    val record = ProducerRecord("ECOMMERCE_NEW_ORDER", value, value)
    producer.send(record) { data: RecordMetadata, ex: Exception? ->
        ex?.let { ex.printStackTrace() }
            ?: println("sucesso enviando ${data.topic()}:::partition ${data.partition()}/ offset ${data.offset()}/ timestamp ${data.timestamp()}")
    }.get()
}

fun properties() = Properties().apply {
    setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:29092")
    setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
    setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
}