package magicsquare.quartumbackend.services

import magicsquare.quartumbackend.exceptions.InventoryServiceException
import magicsquare.quartumbackend.persistance.entity.User
import magicsquare.quartumbackend.persistance.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService (val repository: UserRepository) {

    fun findById(id: Long) : User {
        val user = repository.findByIdOrNull(id)

        return user ?: throw InventoryServiceException("Credentials for $id not found")
    }
}