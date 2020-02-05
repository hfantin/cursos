package com.github.hfantin.loja.client

import com.github.hfantin.loja.dto.InfoFornecedorDTO
import com.github.hfantin.loja.dto.InfoPedidoDTO
import com.github.hfantin.loja.dto.ItemDaCompraDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient("fornecedor")
interface FornecedorClient {

    @RequestMapping("/info/{estado}")
    fun getInfoProEstado(@PathVariable estado: String?): InfoFornecedorDTO

    @RequestMapping(method = [RequestMethod.POST], value=["pedido"])
    fun realizaPedido(itens: List<ItemDaCompraDTO>) : InfoPedidoDTO
}