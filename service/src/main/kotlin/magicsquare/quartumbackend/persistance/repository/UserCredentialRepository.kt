package magicsquare.quartumbackend.persistance.repository

import magicsquare.quartumbackend.persistance.entity.UserCredential
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserCredentialRepository : JpaRepository<UserCredential, Long> {
}