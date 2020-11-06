package com.github.hfantin.controllers

import com.github.hfantin.entidades.Ordem
import com.github.hfantin.repositories.OrdemRepository
import javax.annotation.security.RolesAllowed
import javax.inject.Inject
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.MediaType

@Path("/v1/ordens")

class OrdemController {

    @Inject
    private lateinit var ordemRepository: OrdemRepository

    @POST
    @Transactional
    @RolesAllowed("user")
    @Consumes(MediaType.APPLICATION_JSON)
    fun incluir(@Valid ordem: Ordem) = ordemRepository.persist(ordem)
}