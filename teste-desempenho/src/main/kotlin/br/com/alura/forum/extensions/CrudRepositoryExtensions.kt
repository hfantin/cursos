package br.com.alura.forum.extensions

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity

fun <T, ID> CrudRepository<T, ID>.findByIdOrNotFound(id: ID, action: (T) -> Any) = this.findByIdOrNull(id)?.let { ResponseEntity.ok(action(it)) } ?: ResponseEntity.notFound().build()