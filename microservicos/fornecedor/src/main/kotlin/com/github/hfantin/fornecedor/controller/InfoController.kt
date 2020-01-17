package com.github.hfantin.fornecedor.controller

import com.github.hfantin.fornecedor.model.InfoFornecedor
import com.github.hfantin.fornecedor.service.InfoService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/info")
class InfoController {

    @Autowired
    private lateinit var infoService: InfoService

    private val logger = LoggerFactory.getLogger(javaClass)

    @RequestMapping("/{estado}")
    fun getInfoPorEstado(@PathVariable estado: String?): InfoFornecedor? {
        logger.info("obter info do estado {}", estado)
        return infoService.getInfoPorEstado(estado)
    }
}
