package com.github.hfantin.loja.repository

import com.github.hfantin.loja.model.Compra
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CompraRepository : CrudRepository<Compra, Long>