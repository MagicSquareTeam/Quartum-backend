package magicsquare.quartumbackend.persistance.repository

import magicsquare.quartumbackend.persistance.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    override fun findById(id: Long): Optional<User>
}
