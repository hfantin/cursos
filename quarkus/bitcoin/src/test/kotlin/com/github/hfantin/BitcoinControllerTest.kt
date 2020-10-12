package com.github.hfantin

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test

@QuarkusTest
class BitcoinControllerTest {

    @Test
    fun testarSeStatusCodeRequisicaoEh200() {
        given()
                .`when`().get("/v1/bitcoins")
                .then()
                .statusCode(200)
    }

}