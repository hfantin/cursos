package com.github.hfantin.loja.client

import com.github.hfantin.loja.dto.*
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient("transportador")
interface TransportadorClient {

    @RequestMapping(method = [RequestMethod.POST], value=["entrega"])
    fun reservaEntrega(entregaDto: InfoEntregaDTO) : VoucherDTO
}