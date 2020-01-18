package com.github.hfantin.fornecedor.controller

import com.github.hfantin.fornecedor.dto.ItemDoPedidoDTO
import com.github.hfantin.fornecedor.service.PedidoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("pedido")
class PedidoController {
    @Autowired
    private lateinit var pedidoService: PedidoService

    @RequestMapping(method = [RequestMethod.POST])
    fun realizaPedido(@RequestBody produtos: List<ItemDoPedidoDTO>) = pedidoService.realizaPedido(produtos)

    @RequestMapping("/{id}")
    fun getPedidoPorId(@PathVariable id: Long) = pedidoService.getPedidoPorId(id)

}