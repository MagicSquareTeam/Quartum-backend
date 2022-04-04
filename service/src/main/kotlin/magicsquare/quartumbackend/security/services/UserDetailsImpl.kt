package magicsquare.quartumbackend.security.services

import com.fasterxml.jackson.annotation.JsonIgnore
import magicsquare.quartumbackend.persistance.entity.Role
import magicsquare.quartumbackend.persistance.entity.UserCredential
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class UserDetailsImpl(
    private val id: Long,
    private val email: String,
    @JsonIgnore
    private val password: CharArray,
    private val authorities: MutableCollection<out GrantedAuthority>
) : UserDetails {

    companion object{
        fun build(userCredential: UserCredential, roles: MutableSet<Role>): UserDetailsImpl{
            val authorities = roles.stream()
                .map { role -> SimpleGrantedAuthority(role.roleName!!.name) }
                .toList()
            return UserDetailsImpl(
                userCredential.id!!,
                userCredential.email!!,
                userCredential.password!!,
                authorities
            )
        }
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = authorities

    override fun getPassword(): String = String(password)

    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

    fun getUserId(): Long = id

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserDetailsImpl

        if (id != other.id) return false
        if (email != other.email) return false
        if (!password.contentEquals(other.password)) return false
        if (authorities != other.authorities) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.contentHashCode()
        result = 31 * result + authorities.hashCode()
        return result
    }


}