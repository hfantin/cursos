package br.com.alura.forum.model

import javax.persistence.Entity

enum class StatusTopico {
    NAO_RESPONDIDO,
    NAO_SOLUCIONADO,
    SOLUCIONADO,
    FECHADO
}