package magicsquare.quartumbackend.services

import magicsquare.quartumbackend.dto.UserCredentialDto
import magicsquare.quartumbackend.exceptions.InventoryServiceException
import magicsquare.quartumbackend.persistance.entity.UserCredential
import magicsquare.quartumbackend.persistance.mapper.CommonMapper
import magicsquare.quartumbackend.persistance.repository.UserCredentialRepository
import org.springframework.stereotype.Service

@Service
class UserCredentialService(
    private val repository: UserCredentialRepository,
    val mapper: CommonMapper<UserCredentialDto, UserCredential>
) {
    fun findByEmail(email: String): UserCredential {
        val userCredential = repository.findByEmail(email)

        return userCredential ?: throw InventoryServiceException("Credentials for $email not found")
    }

    fun existByEmail(email: String) = repository.existsByEmail(email)
}