package com.github.hfantin.fornecedor.controller

import com.github.hfantin.fornecedor.service.ProdutoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/produto")
class ProdutoController {
    @Autowired
    private lateinit var produtoService: ProdutoService

    @RequestMapping("/{estado}")
    fun getProdutosPorEstado(@PathVariable("estado") estado: String) = produtoService.getProdutosPorEstado(estado)
}