package com.github.hfantin.controllers

import com.github.hfantin.entidades.Ordem
import com.github.hfantin.repositories.OrdemRepository
import com.github.hfantin.repositories.UsuarioRepository
import javax.annotation.security.RolesAllowed
import javax.inject.Inject
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.SecurityContext

@Path("/v1/ordens")

class OrdemController {

    @Inject
    private lateinit var ordemRepository: OrdemRepository

    @Inject
    private lateinit var usuarioRepository: UsuarioRepository

    @POST
    @Transactional
    @RolesAllowed("user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Throws(Exception::class)
    fun incluir(@Context securityContext: SecurityContext, @Valid ordem: Ordem) {
        val usuarioOptional = usuarioRepository.findByIdOptional(ordem.userId)
        val usuario = usuarioOptional.orElseThrow()
        if(usuario.username != securityContext.userPrincipal.name) {
            throw Exception("O usuário logado é diferente do userId")
        }

        ordemRepository.persist(ordem)
    }

    @GET
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    fun listar() = ordemRepository.listAll()
}