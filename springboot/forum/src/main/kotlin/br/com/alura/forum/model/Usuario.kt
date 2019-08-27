package br.com.alura.forum.model

import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
data class Usuario(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val nome: String? = null,
        val email: String? = null,
        val senha: String? = null,
        @ManyToMany(fetch = FetchType.EAGER)
        val perfis: List<Perfil> = mutableListOf()
) : UserDetails {
    override fun getAuthorities() = perfis

    override fun isEnabled() = true

    override fun getUsername() = nome

    override fun isCredentialsNonExpired() = true

    override fun getPassword() = senha

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

}
