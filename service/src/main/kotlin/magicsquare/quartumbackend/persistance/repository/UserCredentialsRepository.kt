package magicsquare.quartumbackend.persistance.repository

import magicsquare.quartumbackend.persistance.entity.UserCredential
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserCredentialsRepository : JpaRepository<UserCredential, Long> {
    @Query("select u from UserCredential u where u.email = ?1")
    fun findByEmail(email: String)
}