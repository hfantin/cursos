package com.github.hfantin.fornecedor.service

import com.github.hfantin.fornecedor.model.InfoFornecedor
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InfoService {

    @Autowired
    private lateinit var infoRepository: InfoRepository

    fun getInfoPorEstado(estado: String?) = infoRepository.findByEstado(estado)
}