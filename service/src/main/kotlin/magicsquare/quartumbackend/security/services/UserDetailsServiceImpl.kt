package magicsquare.quartumbackend.security.services

import magicsquare.quartumbackend.services.UserCredentialService
import magicsquare.quartumbackend.services.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsServiceImpl (
    val userCredentialService: UserCredentialService,
    val userService: UserService,
    val userDetailsImpl: UserDetailsImpl
) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(email: String): UserDetails? {
        val credentials = userCredentialService.findByEmail(email)

        val users = userService.findById(credentials.id!!)

        return userDetailsImpl.build(credentials, users.roles)
    }
}
