package br.com.alura.forum.config.security

import br.com.alura.forum.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AutenticacaoService : UserDetailsService{

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository

    override fun loadUserByUsername(username: String?) = usuarioRepository.findByEmail(username) ?: throw UsernameNotFoundException("Dados inválidos")

}