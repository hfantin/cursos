package com.github.hfantin.loja.controller

import com.github.hfantin.loja.controller.dto.CompraDTO
import com.github.hfantin.loja.model.Compra
import com.github.hfantin.loja.service.CompraService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/compra")
class CompraController {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var compraService: CompraService


    @RequestMapping(method = [RequestMethod.POST])
    fun realizaCompra(@RequestBody compra: CompraDTO) = compraService.realizaCompra(compra)
}