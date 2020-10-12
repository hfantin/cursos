package com.github.hfantin.controllers

import com.github.hfantin.services.BitcoinService
import org.eclipse.microprofile.rest.client.inject.RestClient
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/v1/bitcoins")

class BitcoinController {

    @Inject
    @RestClient
    private lateinit var bitcoinService: BitcoinService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun listar() = bitcoinService.listar()
}