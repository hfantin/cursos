package com.github.hfantin.repositories

import com.github.hfantin.entidades.Ordem
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.junit.mockito.InjectMock
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

@QuarkusTest
class OrdemRepositoryTest {

    @InjectMock
    private lateinit var ordemRepository: OrdemRepository

    @Test
    fun testarSeListAllRetornaOrdens() {
        val ordens = listOf(Ordem(), Ordem())
        Mockito.`when`(ordemRepository.listAll()).thenReturn(ordens)
        Assertions.assertSame(ordens[1], ordemRepository.listAll()[1])
    }


}