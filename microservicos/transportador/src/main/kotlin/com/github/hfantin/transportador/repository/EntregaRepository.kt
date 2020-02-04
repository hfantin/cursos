package com.github.hfantin.transportador.repository

import com.github.hfantin.transportador.model.Entrega
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EntregaRepository : CrudRepository<Entrega, Long>