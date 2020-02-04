package com.github.hfantin.transportador.controller

import com.github.hfantin.transportador.dto.EntregaDTO
import com.github.hfantin.transportador.dto.VoucherDTO
import com.github.hfantin.transportador.service.EntregaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/entrega")
class EntregaController {
    @Autowired
    private lateinit var entregaService: EntregaService

    @RequestMapping(method = [RequestMethod.POST])
    fun reservaEntrega(@RequestBody pedidoDTO: EntregaDTO) = entregaService.reservaEntrega(pedidoDTO)

}