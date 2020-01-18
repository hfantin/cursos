package com.github.hfantin.fornecedor.repository

import com.github.hfantin.fornecedor.model.Pedido
import org.springframework.data.repository.CrudRepository

interface PedidoRepository : CrudRepository<Pedido, Long>