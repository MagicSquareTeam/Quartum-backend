package magicsquare.quartumbackend.security.services

import magicsquare.quartumbackend.services.UserCredentialService
import magicsquare.quartumbackend.services.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * User details service impl - отвечает за поиск пользовательских данных в БД
 *
 * @property userCredentialService
 * @property userService
 * @constructor Создаёт пустую  User details service impl
 */
@Service
class UserDetailsServiceImpl (
    val userCredentialService: UserCredentialService,
    val userService: UserService
) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        val credentials = userCredentialService.findByUsername(username)

        val users = userService.findById(credentials.id!!)

        return UserDetailsImpl.build(credentials, users.roles)
    }
}
