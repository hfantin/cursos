package com.github.hfantin.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1/error")
class GreetingController {

    @GetMapping
    fun error(): String {
        throw Exception("erro generico")
    }
}