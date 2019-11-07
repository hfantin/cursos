package com.github.hfantin.sender

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/orders")
class OrderController {

    @Autowired
    private lateinit var orderQueueSender: OrderQueueSender

    @RequestMapping(method = [ RequestMethod.POST ])
    fun send(@RequestBody order: String) {
        println("enviando ordem : $order")
        orderQueueSender.send(order)
    }

}
