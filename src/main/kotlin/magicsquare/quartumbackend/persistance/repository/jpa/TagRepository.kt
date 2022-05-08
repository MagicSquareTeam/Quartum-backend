package magicsquare.quartumbackend.persistance.repository.jpa;

import magicsquare.quartumbackend.persistance.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

/**
 * Репозиторий Tag
 *
 */
interface TagRepository : JpaRepository<Tag, Int> {
    /**
     * Ищет тэг по имени
     *
     * @param name
     * @return тэг
     */
    fun findByName(name: String): Optional<Tag>
}
