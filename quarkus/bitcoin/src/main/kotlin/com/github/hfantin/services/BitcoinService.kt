package com.github.hfantin.services

import com.github.hfantin.models.Bitcoin
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/bitcoins")
@RegisterRestClient(configKey = "bitcoin-api")
interface BitcoinService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun listar(): List<Bitcoin>
}