package br.com.alura.forum.config.validacao

import br.com.alura.forum.dto.ErroFormularioDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class ErroValidacaoHandler {

    @Autowired
	private lateinit var messageSource: MessageSource

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException::class)
    fun handle(exception: MethodArgumentNotValidException) =
            exception.bindingResult.fieldErrors.map { ErroFormularioDto(it.field, messageSource.getMessage(it, LocaleContextHolder.getLocale())) }

}