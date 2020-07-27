package com.github.hfantin.modelo

import com.github.hfantin.modelo.Autenticavel

class SistemaInterno {

    fun entra(admin: Autenticavel, senha: Int, autenticado: () -> Unit = {}){
        if(admin.autentica(senha)){
            println("Bem vindo ao Bytebank")
            autenticado()
        } else {
            println("Falha na autenticação")
        }
    }

}