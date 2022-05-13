package magicsquare.quartumbackend.persistance.repository.jpa

import magicsquare.quartumbackend.persistance.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * Репозиторий User
 *
 */
@Repository
interface UserRepository : JpaRepository<User, Long> {

    /**
     * Ищет пользователя по id
     *
     * @param id
     * @return пользователь
     */
    override fun findById(id: Long): Optional<User>
}
