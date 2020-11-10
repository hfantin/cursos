package com.github.hfantin.controllers

import com.github.hfantin.entidades.Usuario
import com.github.hfantin.entidades.adicionar
import com.github.hfantin.repositories.UsuarioRepository
import javax.annotation.security.PermitAll
import javax.annotation.security.RolesAllowed
import javax.inject.Inject
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/v1/usuarios")

class UsuarioController {

    @Inject
    private lateinit var usuarioRepository: UsuarioRepository

    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    fun incluir(@Valid usuario: Usuario) {
        usuario.adicionar()
        usuarioRepository.persist(usuario)
    }

    @GET
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    fun listar() = usuarioRepository.listAll()
}