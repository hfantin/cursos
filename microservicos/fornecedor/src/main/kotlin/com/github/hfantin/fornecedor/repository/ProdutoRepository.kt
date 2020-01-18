package com.github.hfantin.fornecedor.repository

import com.github.hfantin.fornecedor.model.Produto
import org.springframework.data.repository.CrudRepository

interface ProdutoRepository : CrudRepository<Produto, Long> {
    fun findByEstado(estado: String): List<Produto>
    fun findByIdIn(ids: List<Long>): List<Produto>
}