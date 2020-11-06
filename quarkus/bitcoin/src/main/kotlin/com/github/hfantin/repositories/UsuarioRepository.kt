package com.github.hfantin.repositories

import com.github.hfantin.entidades.Usuario
import io.quarkus.hibernate.orm.panache.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UsuarioRepository : PanacheRepository<Usuario>