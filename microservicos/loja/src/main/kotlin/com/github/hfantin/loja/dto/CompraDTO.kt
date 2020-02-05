package com.github.hfantin.loja.dto

import com.fasterxml.jackson.annotation.JsonIgnore

data class CompraDTO(
        @JsonIgnore
        var compraId: Long = 0,
        var itens: List<ItemDaCompraDTO>,
        var endereco: EnderecoDTO
)