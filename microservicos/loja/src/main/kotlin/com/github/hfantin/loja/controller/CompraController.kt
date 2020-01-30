package com.github.hfantin.loja.controller

import com.github.hfantin.loja.controller.dto.CompraDTO
import com.github.hfantin.loja.service.CompraService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/compra")
class CompraController {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var compraService: CompraService

    @RequestMapping("/{id}")
	fun getById(@PathVariable("id") id: Long) = compraService.getById(id)


    @RequestMapping(method = [RequestMethod.POST])
    fun realizaCompra(@RequestBody compra: CompraDTO) = compraService.realizaCompra(compra)
}