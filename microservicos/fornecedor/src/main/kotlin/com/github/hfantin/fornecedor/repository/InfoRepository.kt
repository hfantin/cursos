package com.github.hfantin.fornecedor.repository

import com.github.hfantin.fornecedor.model.InfoFornecedor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface InfoRepository : CrudRepository<InfoFornecedor?, Long?> {
    fun findByEstado(estado: String?): InfoFornecedor?
}