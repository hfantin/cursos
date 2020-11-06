package com.github.hfantin.repositories

import com.github.hfantin.entidades.Ordem
import io.quarkus.hibernate.orm.panache.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class OrdemRepository : PanacheRepository<Ordem>