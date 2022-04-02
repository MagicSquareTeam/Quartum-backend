package magicsquare.quartumbackend.service

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import magicsquare.quartumbackend.dto.UserCredentialDto
import magicsquare.quartumbackend.persistance.entity.UserCredential
import magicsquare.quartumbackend.persistance.mapper.UserCredentialsMapper
import magicsquare.quartumbackend.persistance.repository.UserCredentialsRepository
import org.hibernate.Criteria
import org.springframework.stereotype.Service

@Service
class UserCredentialsService(
    repository: UserCredentialsRepository,
    userCredentialsMapper: UserCredentialsMapper,
    sessi
) : AbstractInventoryService<UserCredentialDto,UserCredential, Long>(
    repository,
    userCredentialsMapper, entityManager,
)  {
    @PersistenceContext
    val em: EntityManager


}