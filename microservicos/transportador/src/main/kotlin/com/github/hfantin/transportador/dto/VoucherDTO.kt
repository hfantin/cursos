package com.github.hfantin.transportador.dto

import java.time.LocalDate

data class VoucherDTO(var numero: Long = 0, var previsaoParaEntrega: LocalDate = LocalDate.now())