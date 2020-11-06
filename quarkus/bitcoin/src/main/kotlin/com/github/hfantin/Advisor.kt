package com.github.hfantin

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class Advisor {
    private val logger = LoggerFactory.getLogger(javaClass.simpleName)

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handle(exception: Exception): ErroDto {
        logger.error("Erro genérico:", exception, exception)
        return ErroDto(error = exception.message)
    }

}