package com.github.hfantin.fornecedor.service

import com.github.hfantin.fornecedor.repository.ProdutoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProdutoService {
    @Autowired
    private lateinit var produtoRepository: ProdutoRepository

    fun getProdutosPorEstado(estado: String) = produtoRepository.findByEstado(estado)
}