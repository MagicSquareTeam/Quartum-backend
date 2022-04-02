package magicsquare.quartumbackend.security

import magicsquare.quartumbackend.persistance.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsServiceImpl (
    userRepository: UserRepository,
) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String?): UserDetails {

    }
}