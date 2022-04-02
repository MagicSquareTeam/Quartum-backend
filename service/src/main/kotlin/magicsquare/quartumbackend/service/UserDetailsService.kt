package magicsquare.quartumbackend.service

import magicsquare.quartumbackend.persistance.repository.UserCredentialsRepository
import org.springframework.stereotype.Service

@Service
class UserDetailsService(
    val repository: UserCredentialsRepository
) {

    fun findByEmail(email:String){
        repository.findByEmail(email)
    }
}